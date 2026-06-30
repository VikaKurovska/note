package com.example.note.data.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.note.data.entity.Note
import kotlinx.coroutines.flow.Flow
@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getNotes():Flow<List<Note>>
@Insert
fun addNote(note: Note)

@Query("DELETE FROM note WHERE noteId = :id")
fun deleteNote(id: Int)

    @Query("SELECT * FROM note WHERE noteId = :id")
    suspend fun getNoteById(id: Int): Note?

    @Update
    suspend fun updateNote(note: Note)
}