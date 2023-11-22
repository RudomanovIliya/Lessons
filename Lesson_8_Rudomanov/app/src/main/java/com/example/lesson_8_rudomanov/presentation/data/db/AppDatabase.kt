package com.example.lesson_8_rudomanov.presentation.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lesson_8_rudomanov.presentation.data.db.dao.NoteDao
import com.example.lesson_8_rudomanov.presentation.data.db.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = AppDatabase.VERSION,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        const val NAME = "app_db"
        const val VERSION = 1
    }
}