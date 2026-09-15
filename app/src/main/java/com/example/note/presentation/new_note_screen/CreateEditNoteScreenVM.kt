package com.example.note.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.note.R
import com.example.note.data.entity.Note
import com.example.note.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class NewNoteVM @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {

    val availableColors = listOf(
        R.color.LAVANDA,
        R.color.yellow,
        R.color.pink,
        R.color.blue,
        R.color.green
    )

    // Поля стану для UI
    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _content = MutableStateFlow("")
    val content: StateFlow<String> = _content.asStateFlow()

    private val _selectedColor = MutableStateFlow(availableColors.random())
    val selectedColor: StateFlow<Int> = _selectedColor.asStateFlow()

    // ID поточної нотатки (null = нова нотатка, ще не в базі)
    private var noteId: Int? = null

    init {
        // Автозбереження: чекаємо 1 сек (1000 ms) після зупинки друку
        viewModelScope.launch {
            combine(_title, _content, _selectedColor) { title, content, color ->
                Triple(title, content, color)
            }
                .debounce(1000L)
                .collect { (title, content, color) ->
                    saveNoteToDb(title, content, color)
                }
        }
    }

    // Методи для UI (викликаються при фокусі/введенні тексту)
    fun onTitleChange(newTitle: String) {
        _title.value = newTitle
    }

    fun onContentChange(newContent: String) {
        _content.value = newContent
    }

    fun onColorChange(newColor: Int) {
        _selectedColor.value = newColor
    }

    // Завантаження нотатки при відкритті з головного екрана
    fun getNoteFromDB(id: Int) {
        if (id == -1) return // Нова нотатка

        noteId = id
        viewModelScope.launch {
            repository.getNoteById(id)?.let { note ->
                _title.value = note.title
                _content.value = note.content
                _selectedColor.value = note.color
            }
        }
    }

    // Внутрішнє автозбереження
    private suspend fun saveNoteToDb(title: String, content: String, color: Int) {
        if (title.isBlank() && content.isBlank()) return

        if (noteId == null) {
            // Перше збереження -> INSERT (отримуємо новий ID)
            val newNote = Note(
                title = title,
                content = content,
                color = color,
                timestamp = System.currentTimeMillis()
            )
            val generatedId = repository.insertNote(newNote)
            noteId = generatedId.toInt()
        } else {
            // Повторне збереження -> UPDATE
            val updatedNote = Note(
                id = noteId!!,
                title = title,
                content = content,
                color = color,
                timestamp = System.currentTimeMillis()
            )
            repository.updateNote(updatedNote)
        }
    }

    // Примусове збереження (наприклад, при натисканні кнопки "Назад")
    fun saveOnBack() {
        viewModelScope.launch {
            saveNoteToDb(_title.value, _content.value, _selectedColor.value)
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            repository.deleteNote(id)
        }
    }
}