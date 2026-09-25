package com.example.note.presentation.main_notes_screen

import android.app.Application
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.note.data.database.NoteDatabase
import com.example.note.data.repository.NoteRepository
import com.example.note.data.entity.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainNotesViewModel @Inject constructor (private val repository: NoteRepository) : ViewModel() {

    val noteList: StateFlow<List<Note>> = repository.getActiveNotes()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )

    fun deleteNote(noteId: Int) {
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }
        fun archiveNote(noteId: Int) {
            viewModelScope.launch {
                repository.archiveNote(noteId)
            }
        }

        fun updateNoteColor(note: Note, newColor: Int) {
            viewModelScope.launch {
                repository.updateNoteColor(note, newColor)
            }
        }
    }

/*
class NoteViewModelFactory(val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainNotesViewModel(application) as T
    }
}

 */
