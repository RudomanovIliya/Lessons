package com.example.lesson_8_rudomanov.presentation

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_8_rudomanov.R
import com.example.lesson_8_rudomanov.databinding.FragmentListNotesBinding
import com.example.lesson_8_rudomanov.presentation.data.db.DatabaseClient
import com.example.lesson_8_rudomanov.presentation.data.db.entity.NoteEntity
import kotlinx.coroutines.launch

class ListNotesFragment : Fragment(R.layout.fragment_list_notes) {
    private var navigationController: NavigationController? = null
    private val binding by viewBinding(FragmentListNotesBinding::bind)
    private val notesAdapter = NotesAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationController = (parentFragment as? NavigationController)
            ?: (activity as? NavigationController)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingButtonAdd.setOnClickListener {
            navigationController?.navigate(EditNoteFragment.newInstance(null))
        }

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        setHasOptionsMenu(true)

        binding.recyclerViewNotes.adapter = notesAdapter.apply {
            noteListener = NoteListener { note ->
                navigationController?.navigate(EditNoteFragment.newInstance(note))
            }
            noteLongListener = LongNoteListener { note ->
                val builder: AlertDialog.Builder = AlertDialog.Builder(binding.root.context)
                builder.setMessage(getString(R.string.delete_or_archive))
                builder.setPositiveButton(
                    getString(R.string.arhive)
                ) { _, _ ->
                    lifecycleScope.launch {
                        DatabaseClient.noteDao(binding.root.context).update(
                            NoteEntity(
                                id = note.id,
                                title = note.title,
                                textNote = note.textNote,
                                backgroundColorRes = note.backgroundColorRes,
                                isArchive = true
                            )
                        )
                    }
                }
                builder.setNegativeButton(
                    getString(R.string.delete)
                ) { _, _ ->
                    lifecycleScope.launch {
                        DatabaseClient.noteDao(binding.root.context).deleteById(note.id)
                    }
                }
                val alert: AlertDialog = builder.create()
                alert.show()
            }
        }
        loadNotes()
    }

    private var notesList = mutableListOf<NoteEntity>()
    private fun loadNotes() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                DatabaseClient.noteDao(binding.root.context).getNotesFlow().collect { notes ->
                    notesList.clear()
                    if (!notes.isNullOrEmpty()) {
                        for (note in notes) {
                            if (!note.isArchive) {
                                notesList.add(note)
                            }
                        }
                        if (!notesList.isNullOrEmpty()) {
                            notesAdapter.setList(notesList)
                        } else {
                            binding.progressBar.visibility = View.GONE
                            binding.textViewError.visibility = View.VISIBLE
                            binding.recyclerViewNotes.visibility = View.GONE
                            binding.textViewError.text = getString(R.string.data_in_archive)
                        }
                        binding.progressBar.visibility = View.GONE
                    } else {
                        binding.progressBar.visibility = View.GONE
                        binding.textViewError.visibility = View.VISIBLE
                        binding.recyclerViewNotes.visibility = View.GONE
                        binding.textViewError.text = getString(R.string.no_data)
                    }
                }
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                binding.textViewError.visibility = View.VISIBLE
                binding.recyclerViewNotes.visibility = View.GONE
                binding.textViewError.text = "ERROR ${e.message}"
            }
        }
    }

    fun filterList(text: String) {
        var filteredList = mutableListOf<NoteEntity>()
        for (note in notesList) {
            if ((note.title != null && note.title.toLowerCase()
                    .contains(text.toLowerCase())) || (note.textNote != null && note.textNote.toLowerCase()
                    .contains(text.toLowerCase()))
            ) {
                filteredList.add(note)
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(binding.root.context, getString(R.string.no_data), Toast.LENGTH_SHORT)
                .show()
            notesAdapter.setList(filteredList)
        } else {
            notesAdapter.setList(filteredList)
        }
    }

    companion object {
        fun newInstance(): ListNotesFragment {
            return ListNotesFragment()
        }
    }
}