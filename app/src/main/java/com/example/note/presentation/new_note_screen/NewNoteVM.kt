package com.example.note.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.note.data.entity.Note
import com.example.note.data.repository.NoteRepository
import com.example.note.R
import kotlinx.coroutines.launch

// Якщо ви робите ручну збірку (без Hilt), репозиторій передається в конструктор
class NewNoteVM(private val repository: NoteRepository) : ViewModel() {

    // 🚀 Цю функцію ми будемо викликати при натисканні на твою кнопку збереження
    fun saveNote(title: String, content: String) {

        // 1. Беремо список твоїх кольорів з colors.xml і вибираємо випадковий
        val availableColors = listOf(
            R.color.LAVANDA,
            R.color.yellow,
            R.color.pink,
            R.color.blue,
            R.color.green
        )
        val randomColorId = availableColors.random()

        // 2. Відкриваємо корутину (viewModelScope.launch)
        // Запис у базу даних — це важка робота, її не можна робити в головному потоці інтерфейсу!
        viewModelScope.launch {

            // 3. Зліплюємо нову нотатку до купи
            val newNote = Note(
                title = title,
                content = content,
                color = randomColorId // Передаємо ID кольору як Int
                // timestamp запишеться сам (System.currentTimeMillis()), бо ми так вказали в Note.kt
            )

            // 4. Віддаємо нотатку репозиторію
            repository.insertNote(newNote)
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