package com.example.lesson_8_rudomanov.presentation

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_8_rudomanov.presentation.data.db.entity.NoteEntity

class NotesAdapter : RecyclerView.Adapter<NoteViewHolder>(){
    private val notes = mutableListOf<NoteEntity>()
    lateinit var noteListener: NoteListener
    lateinit var noteLongListener: LongNoteListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(parent, noteListener, noteLongListener)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(notes: MutableList<NoteEntity>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }
}