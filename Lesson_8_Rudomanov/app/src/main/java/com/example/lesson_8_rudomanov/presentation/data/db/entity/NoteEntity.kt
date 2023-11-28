package com.example.lesson_8_rudomanov.presentation.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = NoteEntity.TABLE_NAME)
data class NoteEntity(
    @PrimaryKey val id: String,
    val title: String?,
    val textNote: String?,
    val backgroundColorRes: Int?,
    var isArchive: Boolean,
) {
    companion object {
        const val TABLE_NAME = "notes"
    }
}