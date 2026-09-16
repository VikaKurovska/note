package com.example.note.data.database
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.note.data.entity.Note
import kotlinx.coroutines.flow.Flow
@Dao
interface NoteDao {

@Insert
suspend fun addNote(note: Note): Long

@Query("DELETE FROM note WHERE noteId = :id")
suspend fun deleteNote(id: Int)

    @Query("SELECT * FROM note WHERE noteId = :id")
    suspend fun getNoteById(id: Int): Note?

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM note WHERE isArchived = 0 ORDER BY timestamp DESC")
    fun getActiveNotes(): Flow<List<Note>>
    @Query("SELECT * FROM note WHERE isArchived = 1 ORDER BY timestamp DESC")
    fun getArchivedNotes(): Flow<List<Note>>
    @Query("UPDATE note SET isArchived = 1 WHERE noteId = :id")
    suspend fun archiveNote(id: Int)
    @Query("UPDATE note SET isArchived = 0 WHERE noteId = :id")
    suspend fun unarchiveNote(id: Int)
}