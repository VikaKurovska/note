package com.example.note.data.repository
import com.example.note.data.database.NoteDao
import com.example.note.data.entity.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteRepository(private val noteDao: NoteDao) {
    fun getAllNotes(): Flow<List<Note>> = noteDao.getNotes()
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    suspend fun insertNote(note: Note) {
        coroutineScope.launch() {
            noteDao.addNote(note)
        }
    }

    suspend fun deleteNote(id: Int) {
        coroutineScope.launch() {
            noteDao.deleteNote(id)
        }
    }
    suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)

}
