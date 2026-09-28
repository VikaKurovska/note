package com.example.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.note.navigation.NotesNavGraph
import com.example.note.presentation.theme.NoteTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.note.data.preferences.PreferencesRepository
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var preferencesRepository: PreferencesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDarkTheme by preferencesRepository.isDarkTheme
                .collectAsState(initial = false)

            NoteTheme(
                darkTheme = isDarkTheme
            ) {
                NotesNavGraph()
            }
        }
    }
}
