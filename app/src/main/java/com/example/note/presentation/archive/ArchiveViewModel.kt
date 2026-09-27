package com.example.note.presentation.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.data.entity.Note
import com.example.note.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArchiveViewModel @Inject constructor(private val repository: NoteRepository): ViewModel(){
    val archiveNoteList: StateFlow<List<Note>> = repository.getArchivedNotes()
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.WhileSubscribed(5000L),
            initialValue = emptyList()
        )
    fun unarchiveNote(noteId: Int){
        viewModelScope.launch {
           repository.unarchiveNote(noteId)
        }
    }
    fun deleteNote(noteId: Int){
        viewModelScope.launch {
            repository.deleteNote(noteId)
        }
    }
    fun updateNoteColor(note: Note, newColor: Int){
        viewModelScope.launch {
            repository.updateNoteColor(note, newColor)
        }
    }
}