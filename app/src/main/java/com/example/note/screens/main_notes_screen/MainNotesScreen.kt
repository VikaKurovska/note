package com.example.note.screens.main_notes_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.EmptyNoteScreen
import com.example.note.R
import com.example.note.screens.main_notes_screen.notes_list.NotesList
import com.example.note.screens.main_notes_screen.notes_list.NotesListModes
import com.example.note.model.Note
import com.example.note.model.notesList

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotesScreen(onAddNoteClick: () -> Unit = {}) {
    var currentMode by remember { mutableStateOf(NotesListModes.LIST) }
    var searchText by remember { mutableStateOf("") }
    val filteredNotes = notesList.filter { it.title.contains(searchText, ignoreCase = true) }
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {
                    Text(
                        "Notes",
                        fontSize = 36.sp,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.DarkGray
                ),
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.img_color),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Зміна теми",
                        )
                    }
                    IconButton(onClick = {
                        currentMode = if (currentMode == NotesListModes.LIST)
                            NotesListModes.GRID else NotesListModes.LIST
                    }) {
                        Icon(
                            painter = painterResource(id = if (currentMode == NotesListModes.LIST) R.drawable.img_sort else R.drawable.img_sort_grid),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Сортування",
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddNoteClick() },
                shape = CircleShape,
                containerColor = Color(0xFFFFB74D),
                modifier = Modifier.size(70.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.img_plus),
                    modifier = Modifier.size(28.dp),
                    contentDescription = "Додати нотатку",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(90.dp))
        },
        floatingActionButtonPosition = FabPosition.End,

        bottomBar = {
            BottomAppBar {
                TextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    placeholder = { Text("Search...") },
                    singleLine = true,
                    shape = CircleShape,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.img_search),
                            contentDescription = "search"
                        )

                    }, trailingIcon = {
                        IconButton(onClick = { searchText = "" }) {
                            Icon(
                                painter = painterResource(id = R.drawable.img_cancel),
                                contentDescription = "search"
                            )
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        // Основний контент екрану
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
            ) {
                if (filteredNotes.isEmpty()) {
                    EmptyNoteScreen()
                } else {
                    NotesList(notes = filteredNotes, mode = currentMode)
                }

            }
        }
    }
}


