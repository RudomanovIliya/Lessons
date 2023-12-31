package com.example.lesson_8_rudomanov.presentation

import androidx.fragment.app.Fragment
import com.example.lesson_8_rudomanov.presentation.data.db.entity.NoteEntity

fun interface NoteListener {
    fun onNoteClick(note: NoteEntity)
}

fun interface LongNoteListener {
    fun onNoteLongClick(note: NoteEntity)
}


