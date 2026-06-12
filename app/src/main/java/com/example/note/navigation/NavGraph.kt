package com.example.note.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable // ПЕРЕВІР, ЩОБ ЦЕЙ ІМПОРТ БУВ!
import androidx.navigation.compose.rememberNavController
import com.example.note.new_note_creation.CreateNote
import com.example.note.screens.main_notes_screen.NotesScreen

@Composable
fun NotesNavGraph() {
    val navController = rememberNavController()

    // 1. Відкриваємо фігурні дужки для NavHost
    NavHost(
        navController = navController,
        startDestination = NavRoutes.NotesScreen.route
    ) {
        composable(NavRoutes.CreateNote.route) {
            CreateNote()
        }

        composable(NavRoutes.NotesScreen.route) {
            NotesScreen(
                onAddNoteClick = {
                    navController.navigate(NavRoutes.CreateNote.route)
                }
            )
        }
    } // Закриваємо NavHost
}