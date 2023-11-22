package com.example.lesson_8_rudomanov.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.lesson_8_rudomanov.R
import com.example.lesson_8_rudomanov.databinding.ItemNoteBinding
import com.example.lesson_8_rudomanov.presentation.data.db.entity.NoteEntity

class NoteViewHolder(
    parent: ViewGroup,
    private val noteListener: NoteListener,
    private val noteLongListener: LongNoteListener,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
) {
    private val binding by viewBinding(ItemNoteBinding::bind)

    fun bind(note: NoteEntity) = with(binding) {
        root.setOnClickListener {
            noteListener.onNoteClick(note)
        }
        root.setOnLongClickListener {
            noteLongListener.onNoteLongClick(note)
            true
        }
        textViewTitle.text = note.title
        textViewNote.text = note.textNote
        if (note.backgroundColorRes == null || note.backgroundColorRes == R.color.white) {
            cardViewNote.setCardBackgroundColor(root.context.getColor(R.color.white))
            textViewTitle.setTextColor(root.context.getColor(R.color.black))
            textViewNote.setTextColor(root.context.getColor(R.color.black))
        } else {
            cardViewNote.setCardBackgroundColor(root.context.getColor(note.backgroundColorRes))
            textViewTitle.setTextColor(root.context.getColor(R.color.white))
            textViewNote.setTextColor(root.context.getColor(R.color.white))
        }
    }
}