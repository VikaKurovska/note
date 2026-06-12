package com.example.note.navigation


sealed class NavRoutes(val route: String) {
    object CreateNote : NavRoutes("newNote")
    object NotesScreen : NavRoutes("notesScreen")
}