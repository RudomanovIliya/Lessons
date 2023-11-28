package com.example.lesson_8_rudomanov.presentation.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.lesson_8_rudomanov.presentation.data.db.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert
    suspend fun save(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Query("delete from ${NoteEntity.TABLE_NAME} where id = :id")
    suspend fun deleteById(id: String)

    @Query("select * from ${NoteEntity.TABLE_NAME}")
    fun getNotesFlow(): Flow<MutableList<NoteEntity>?>
}