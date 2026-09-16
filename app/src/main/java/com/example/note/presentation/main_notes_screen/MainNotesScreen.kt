package com.example.note.presentation.main_notes_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.note.EmptyNoteScreen
import com.example.note.R
import com.example.note.presentation.main_notes_screen.composables.NotesList
import com.example.note.presentation.main_notes_screen.composables.NotesListModes

@Composable
fun NotesScreen(
    viewModel: MainNotesViewModel = hiltViewModel(),
    onAddNoteClick: () -> Unit = {},
    onNoteClick: (Int) -> Unit = {},
) {
    //val owner = LocalViewModelStoreOwner.current

    //owner?.let {
//            viewModel(
//            it,
//            "UserViewModel",
//            NoteViewModelFactory(LocalContext.current.applicationContext as Application)
//        )
//
        val notesList by viewModel.noteList.collectAsState()

        var currentMode by remember { mutableStateOf(NotesListModes.LIST) }
        var searchText by remember { mutableStateOf("") }
        val filteredNotes = notesList.filter { it.title.contains(searchText, ignoreCase = true) || it.content.contains(other = searchText,ignoreCase = true ) }

        Scaffold(
            modifier = Modifier.imePadding(),
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
                        },
                        trailingIcon = {
//додати умову
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
            Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                if (filteredNotes.isEmpty()) {
                    EmptyNoteScreen()
                } else {
                    NotesList(
                        notes = filteredNotes,
                        mode = currentMode,
                        onNoteClick = onNoteClick
                    )
                }
            }
        }
    }
