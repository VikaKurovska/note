package com.example.note.presentation.main_notes_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.database.NoteDatabase
import com.example.note.data.repository.NoteRepository
import com.example.note.data.entity.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainNotesViewModel(application: Application) : ViewModel() {
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList: StateFlow<List<Note>> = _noteList
    private val repository: NoteRepository

    init {
        val userDb = NoteDatabase.getInstance(application)
        val userDao = userDb.noteDao()
        repository = NoteRepository(userDao)

        // 3. ОТ ТУТ ГОЛОВНА ВІДМІННІСТЬ ВІД СТАТТІ:
        // Оскільки Flow працює в асинхронному режимі, ми запускаємо корутину
        viewModelScope.launch {
            // Підключаємося до репозиторію і починаємо "слухати" базу даних
            repository.getAllNotes().collect { notes ->
                // Як тільки в базі з'явиться нова нотатка, вона прилетить сюди
                _noteList.value = notes
            }
        }
    }
}
