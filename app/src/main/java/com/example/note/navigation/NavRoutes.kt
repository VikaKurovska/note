package com.example.note.navigation


sealed class NavRoutes(val route: String) {
    object CreateNote : NavRoutes("newNote/{noteId}") {
        // Спеціальна допоміжна функція, яка буде зліплювати рядок з реальним ID
        fun passId(id: Int): String {
            return "newNote/$id"
        }
    }
    object NotesScreen : NavRoutes("notesScreen")
    object MainScreen: NavRoutes("mainScreen")
}