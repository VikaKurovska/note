package com.example.note.presentation.main_notes_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.note.EmptyNoteScreen
import com.example.note.R
import com.example.note.presentation.main_notes_screen.composables.DeleteConfirmationDialog
import com.example.note.presentation.main_notes_screen.composables.NoteActionBottomSheet
import com.example.note.presentation.main_notes_screen.composables.NotesList
import com.example.note.presentation.main_notes_screen.composables.NotesListModes
import com.example.note.presentation.main_notes_screen.composables.SearchField
import com.example.note.presentation.theme.NoteColors

@Composable
fun MainNotesScreen(
    viewModel: MainNotesViewModel = hiltViewModel(),
    onAddNoteClick: () -> Unit = {},
    onNoteClick: (Int) -> Unit = {},
) {
    val notesList by viewModel.noteList.collectAsState()

    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedNoteId by remember { mutableStateOf<Int?>(null) }

    var currentMode by remember { mutableStateOf(NotesListModes.LIST) }
    var searchText by remember { mutableStateOf("") }
    var searchBarState by remember { mutableStateOf(false) }

    val filteredNotes = notesList.filter {
        it.title.contains(searchText, ignoreCase = true) ||
                it.content.contains(searchText, ignoreCase = true)
    }

    BackHandler(enabled = searchBarState || selectedNoteId != null) {
        if (selectedNoteId != null) {
            selectedNoteId = null
        } else {
            searchBarState = false
            searchText = ""
        }
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {
                    if (!searchBarState) {
                        Text(
                            text = "Notes",
                            fontSize = 36.sp,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    } else {
                        SearchField(
                            searchText = searchText,
                            onValueChange = { value -> searchText = value },
                            onCancelClick = { searchText = "" }
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.DarkGray
                ),
                actions = {
                    IconButton(onClick = {
                        searchBarState = !searchBarState
                        searchText = ""
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Search Bar"
                        )
                    }

                    IconButton(onClick = {
                        currentMode = if (currentMode == NotesListModes.LIST)
                            NotesListModes.GRID else NotesListModes.LIST
                    }) {
                        Icon(
                            painter = painterResource(
                                id = if (currentMode == NotesListModes.LIST)
                                    R.drawable.img_sort else R.drawable.img_sort_grid
                            ),
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Сортування"
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
                modifier = Modifier.size(70.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.img_plus),
                    modifier = Modifier.size(28.dp),
                    contentDescription = "Додати нотатку",
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (filteredNotes.isEmpty()) {
                EmptyNoteScreen()
            } else {
                NotesList(
                    notes = filteredNotes,
                    mode = currentMode,
                    onNoteClick = onNoteClick,
                    onLongClick = { id ->
                        selectedNoteId = id // Записуємо ID затиснутої нотатки
                    }
                )
            }
        }
    }

    // BottomSheet відкривається, коли обрана нотатка
    if (selectedNoteId != null) {
        val selectedNote = notesList.find { it.id == selectedNoteId }

        NoteActionBottomSheet(
            note = selectedNote,
            colors = NoteColors.composeColors,
            onDismiss = {
                selectedNoteId = null
            },
            onColorSelected = { selectedColor ->
                selectedNote?.let { note ->
                    viewModel.updateNoteColor(note, selectedColor.toArgb())
                }
                selectedNoteId = null
            },
            onArchiveClick = {
                selectedNoteId?.let { id ->
                    viewModel.archiveNote(id)
                }
                selectedNoteId = null
            },
            onDeleteClick = {
                showDeleteDialog = true
            }
        )
    }
    if (showDeleteDialog) {
        DeleteConfirmationDialog(
            onConfirm = {
                selectedNoteId?.let { id ->
                    viewModel.deleteNote(id)
                }
                showDeleteDialog = false
                selectedNoteId = null
            },
            onDismiss = {
                showDeleteDialog = false
            }
        )
    }
}