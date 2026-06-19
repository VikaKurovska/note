package com.example.note.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.note.presentation.new_note_screen.CreateNote
import com.example.note.presentation.main_notes_screen.NotesScreen
import com.example.note.data.database.NoteDatabase // 👈 Перевір свій шлях до бази даних
import com.example.note.data.repository.NoteRepository // 👈 Перевір шлях до репозиторію
import com.example.note.presentation.viewmodel.NewNoteVM
import com.example.note.presentation.viewmodel.NewNoteViewModelFactory

@Composable
fun NotesNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.NotesScreen.route
    ) {
        composable(NavRoutes.NotesScreen.route) {
            NotesScreen(
                onAddNoteClick = {
                    navController.navigate(NavRoutes.CreateNote.route)
                }
            )
        }

        composable(NavRoutes.CreateNote.route) {
            // 🛠 ЗБИРАЄМО РУЧНИЙ ВОДОПРОВІД ДЛЯ НОВОГО ЕКРАНА САМЕ ТУТ:
            val context = LocalContext.current

            // 1. Створюємо/беремо інстанцію бази даних
            // Примітка: якщо твій клас бази називається NoteDatabase, перевір назву методу (getInstance або замініть на свій)
            val database = NoteDatabase.getInstance(context.applicationContext)

            // 2. Створюємо Репозиторій і даємо йому DAO
            val repository = NoteRepository(database.noteDao())

            // 3. Створюємо Фабрику, яку ми щойно дописали, і передаємо туди наш репозиторій
            val factory = NewNoteViewModelFactory(repository)

            // 4. Просимо Android дати нам ViewModel, зібрану по цій фабриці
            val newNoteViewModel: NewNoteVM = viewModel(factory = factory)

            // 5. Передаємо готову ViewModel в наш екран
            CreateNote(
                viewModel = newNoteViewModel,
                onBackClick = {
                    // Краще використовувати popBackStack(), щоб закривати поточний екран,
                    // а не плодити нові копії головного екрана в пам'яті
                    navController.popBackStack()
                }
            )
        }
    }
}