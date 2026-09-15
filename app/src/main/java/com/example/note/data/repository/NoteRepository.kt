package com.example.note.data.repository
import com.example.note.data.database.NoteDao
import com.example.note.data.entity.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor (private val noteDao: NoteDao) {

    fun getActiveNotes(): Flow<List<Note>> = noteDao.getActiveNotes()
    fun getArchivedNotes(): Flow<List<Note>> = noteDao.getArchivedNotes()

    suspend fun insertNote(note: Note): Long {
        return noteDao.addNote(note)
    }

    suspend fun deleteNote(id: Int) = noteDao.deleteNote(id)

    suspend fun getNoteById(id: Int): Note? = noteDao.getNoteById(id)

    suspend fun updateNote(note: Note) = noteDao.updateNote(note)
    suspend fun archiveNote(id: Int) = noteDao.archiveNote(id)
    suspend fun unarchiveNote(id: Int) = noteDao.unarchiveNote(id)
}
