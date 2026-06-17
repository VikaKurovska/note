package com.example.note.data.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
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
}