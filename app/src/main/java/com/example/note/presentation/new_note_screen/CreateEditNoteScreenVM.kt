package com.example.note.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.note.data.entity.Note
import com.example.note.data.repository.NoteRepository
import com.example.note.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.sql.Timestamp

class NewNoteVM(private val repository: NoteRepository) : ViewModel() {
    val availableColors = listOf(
        R.color.LAVANDA,
        R.color.yellow,
        R.color.pink,
        R.color.blue,
        R.color.green
    )
    private val _noteState = MutableStateFlow<Note?>(null)
    val noteState = _noteState.asStateFlow()
    fun saveNote(title: String, content: String) {

        val randomColorId = availableColors.random()
        viewModelScope.launch {
            val newNote = Note(
                title = title,
                content = content,
                color = randomColorId // Передаємо ID кольору як Int
            )
            repository.insertNote(newNote)
        }
    }

        fun getNoteFromDB(id: Int){
            if(id == -1) return
            viewModelScope.launch {
                _noteState.value = repository.getNoteById(id)
            }
        }
        fun updateNote(id:Int, title: String, content: String, timestamp: Long) {
            viewModelScope.launch {
                val currentColor = _noteState.value?.color ?: availableColors.random()
                val updatedNote = Note(
                    id = id,
                    title = title,
                    content = content,
                    color = currentColor, // 👈 Тепер усе скомпілюється!
                    timestamp = timestamp
                )
                repository.updateNote(updatedNote)
            }
        }
            fun deleteNote(id:Int){
                viewModelScope.launch {
                    val currentColor = _noteState.value?.color ?: availableColors.random()
                    val noteToDelete = Note(
                        id = id,
                        title = "",
                        content = "",
                        color = currentColor
                    )

                    repository.deleteNote(id)

                    _noteState.value = null
                }
            }
        }





class NewNoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewNoteVM::class.java)) {
            return NewNoteVM(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}