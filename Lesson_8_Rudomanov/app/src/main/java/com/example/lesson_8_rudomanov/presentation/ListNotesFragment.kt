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
import java.util.Locale

class ListNotesFragment : Fragment(R.layout.fragment_list_notes) {
    private var navigationController: NavigationController? = null
    private val binding by viewBinding(FragmentListNotesBinding::bind)
    private val notesAdapter = NotesAdapter()
    private var notesList = mutableListOf<NoteEntity>()

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

    private fun loadNotes() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = View.VISIBLE
                DatabaseClient.noteDao(binding.root.context).getNotesFlow().collect { notes ->
                    notesList.clear()
                    if (!notes.isNullOrEmpty()) {
                        notesList = notes.filter { !it.isArchive }.toMutableList()
                        if (notesList.isNotEmpty()) {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val myActionMenuItem = menu.findItem(R.id.action_search)
        val searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun filterList(text: String) {
        val filteredList = notesList.filter {
            (it.title != null && it.title.lowercase()
                .contains(text.lowercase(Locale.ROOT))) || (it.textNote != null && it.textNote.lowercase()
                .contains(text.lowercase()))
        }.toMutableList()
        if (filteredList.isEmpty()) {
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