package com.example.note.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.note.presentation.main_notes_screen.NotesScreen
import com.example.note.data.database.NoteDatabase // 👈 Перевір свій шлях до бази даних
import com.example.note.data.repository.NoteRepository // 👈 Перевір шлях до репозиторію
import com.example.note.presentation.main.MainScreen
import com.example.note.presentation.new_note_screen.CreateEditNoteScreen
import com.example.note.presentation.viewmodel.NewNoteVM

@Composable
fun NotesNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.MainScreen.route
    ) {
        composable(NavRoutes.MainScreen.route) {
            MainScreen(
                onAddNoteClick = {
                    navController.navigate(NavRoutes.CreateNote.passId(-1))
                },
                onNoteClick = { id ->
                    navController.navigate(NavRoutes.CreateNote.passId(id))

                }
            )
        }

        composable(route = NavRoutes.CreateNote.route,
            arguments = listOf(navArgument("noteId") { type = NavType.IntType })) {
            backStackEntry ->

            val noteId = backStackEntry.arguments?.getInt("noteId") ?: -1
//            val context = LocalContext.current
//            val database = NoteDatabase.getInstance(context.applicationContext)
//
//            val repository = NoteRepository(database.noteDao())
//
//            val factory = NewNoteViewModelFactory(repository)

            val newNoteViewModel: NewNoteVM = hiltViewModel()

            CreateEditNoteScreen(
                viewModel = newNoteViewModel,
                id = noteId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}