package com.example.note.presentation.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

import androidx.navigation.NavGraph.Companion.findStartDestination

import androidx.navigation.compose.composable
import com.example.note.presentation.archive.ArchiveScreen
import com.example.note.presentation.main_notes_screen.NotesScreen
import com.example.note.presentation.profile.SettingsScreen


// MainScreen.kt буде зовнішньою «оболонкою» (Scaffold), яка тримає нижнє меню NavigationBar з трьома вкладками (Нотатки / Архів / Профіль) і підставляє всередину обраний екран.
sealed class MainTab(val route:String, val title:String, val icon: ImageVector) {
    data object Notes : MainTab("home", "Main", Icons.Default.Home)
    data object Settings : MainTab("settings", "Tools", Icons.Default.Settings)
    data object Archive : MainTab("archive", "ArchiveNote", Icons.Default.Archive)

    companion object {
        val items = listOf(Notes, Settings, Archive)
    }
}

@Composable
fun MainScreen(
    onAddNoteClick: () -> Unit,
    onNoteClick: () -> Unit
) {
    val tabNavController = rememberNavController()
    val navBackStackEntry by tabNavController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            NavigationBar {
                MainTab.items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                                if (currentRoute != screen.route) {
                                    tabNavController.navigate(screen.route) {
                                        // Очищает стек до начального экрана, чтобы не копить историю
                                        popUpTo(tabNavController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Избегает создания копий одного и того же экрана при повторном клике
                                        launchSingleTop = true
                                        // Восстанавливает состояние экрана (например, прокрутку списков)
                                        restoreState = true
                                    }
                                }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
        navController = tabNavController,
        startDestination = MainTab.Notes.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(MainTab.Notes.route) { NotesScreen() }
        composable(MainTab.Archive.route) { ArchiveScreen() }
        composable(MainTab.Settings.route) { SettingsScreen() }
    }
    }
}