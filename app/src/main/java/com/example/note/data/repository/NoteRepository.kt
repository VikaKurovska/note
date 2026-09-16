package com.example.note.data.repository
import com.example.note.data.database.NoteDao
import com.example.note.data.entity.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor (private val noteDao: NoteDao) {

    fun getActiveNotes(): Flow<List<Note>> = noteDao.getActiveNotes()
    fun getArchivedNotes(): Flow<List<Note>> = noteDao.getArchivedNotes()

    suspend fun insertNote(note: Note): Long = withContext(Dispatchers.IO) {
        noteDao.addNote(note)
    }

    suspend fun deleteNote(id: Int) = withContext(Dispatchers.IO) {
        noteDao.deleteNote(id)
    }

    suspend fun getNoteById(id: Int): Note? = withContext(Dispatchers.IO) {
        noteDao.getNoteById(id)
    }

    suspend fun updateNote(note: Note) = withContext(Dispatchers.IO) {
        noteDao.updateNote(note)
    }

    suspend fun archiveNote(id: Int) = withContext(Dispatchers.IO) {
        noteDao.archiveNote(id)
    }

    suspend fun unarchiveNote(id: Int) = withContext(Dispatchers.IO) {
        noteDao.unarchiveNote(id)
    }
}
