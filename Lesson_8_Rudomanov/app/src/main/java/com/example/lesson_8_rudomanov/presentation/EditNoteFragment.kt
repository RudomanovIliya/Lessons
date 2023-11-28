package com.example.lesson_8_rudomanov.presentation

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.telecom.CallEventCallback
import android.view.View
import android.widget.Toast
import androidx.activity.BackEventCompat
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_8_rudomanov.R
import com.example.lesson_8_rudomanov.databinding.FragmentEditNoteBinding
import com.example.lesson_8_rudomanov.presentation.data.db.DatabaseClient
import com.example.lesson_8_rudomanov.presentation.data.db.entity.NoteEntity
import kotlinx.coroutines.launch
import java.util.UUID

private const val KEY_NOTE_TITLE = "key_note_title"
private const val KEY_NOTE_TEXT = "key_note_text"
private const val KEY_NOTE_ID = "key_note_id"
private const val KEY_NOTE_COLOR = "key_note_color"

class EditNoteFragment : Fragment(R.layout.fragment_edit_note) {
    private var navigationController: NavigationController? = null
    private val binding by viewBinding(FragmentEditNoteBinding::bind)
    private var color: Int? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationController = (parentFragment as? NavigationController)
            ?: (activity as? NavigationController)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var checkColor: Int? = null
        if (arguments != null) {
            arguments?.let {
                checkColor = it.getInt(KEY_NOTE_COLOR)
                binding.editTextTitle.setText(it.getString(KEY_NOTE_TITLE))
                binding.editTextNote.setText(it.getString(KEY_NOTE_TEXT))
                color = it.getInt(KEY_NOTE_COLOR)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val idNote = if (arguments != null) {
                arguments?.let {
                    it.getString(KEY_NOTE_ID)!!
                }
            } else {
                UUID.randomUUID().toString()
            }
            val note = NoteEntity(
                idNote!!,
                (binding.editTextTitle.text).toString(),
                (binding.editTextNote.text).toString(),
                color,
                false
            )
            lifecycleScope.launch {
                if (arguments != null) {
                    DatabaseClient.noteDao(binding.root.context).update(note)
                } else {
                    DatabaseClient.noteDao(binding.root.context).save(note)
                }
            }
            navigationController?.back()
        }

        binding.toolbar.setNavigationOnClickListener {
            navigationController?.back()
        }
        binding.toolbar.menu.findItem(R.id.itemChangeColor).setOnMenuItemClickListener {
            val items: MutableList<IconAlert> = mutableListOf<IconAlert>(
                IconAlert(R.color.red),
                IconAlert(R.color.pink),
                IconAlert(R.color.purple),
                IconAlert(R.color.dark_purple),
                IconAlert(R.color.light_purple),
                IconAlert(R.color.light_blue),
                IconAlert(R.color.blue),
                IconAlert(R.color.dark_green),
                IconAlert(R.color.green),
                IconAlert(R.color.dark_yellow),
                IconAlert(R.color.yellow),
                IconAlert(R.color.orange),
                IconAlert(R.color.red_orange),
                IconAlert(R.color.gray),
                IconAlert(R.color.dark_gray),
                IconAlert(R.color.white),
            )

            val builder = AlertDialog.Builder(binding.root.context)
            val dialogView = layoutInflater.inflate(R.layout.recycler_alert, null) as View
            builder.setView(dialogView)
            val alertIconAdapter = AlertIconAdapter()
            alertIconAdapter.setList(items, checkColor)
            builder.setTitle(getString(R.string.chose_color))
            builder.setNegativeButton(
                getString(R.string.cancel)
            ) { _, _ ->
            }
            val dialog = builder.create()
            dialogView.findViewById<RecyclerView>(R.id.recyclerViewIcons).adapter =
                alertIconAdapter.apply {
                    alertListener = AlertListener { icon ->
                        color = icon.iconRes
                        checkColor = icon.iconRes
                        dialog.cancel()
                    }
                }
            dialog.show()
            true
        }
    }

    companion object {
        fun newInstance(note: NoteEntity?): EditNoteFragment {
            return EditNoteFragment().apply {
                if (note != null) {
                    arguments = bundleOf(
                        KEY_NOTE_ID to note.id,
                        KEY_NOTE_TITLE to note.title,
                        KEY_NOTE_TEXT to note.textNote,
                        KEY_NOTE_COLOR to note.backgroundColorRes
                    )
                }
            }
        }
    }
}