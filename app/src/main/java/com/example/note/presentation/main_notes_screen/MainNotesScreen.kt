package com.example.note.presentation.main_notes_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import com.example.note.presentation.main_notes_screen.composables.SearchField
import org.w3c.dom.Text

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
    val filteredNotes = notesList.filter {
        it.title.contains(searchText, ignoreCase = true) || it.content.contains(
            other = searchText,
            ignoreCase = true
        )
    }
    var searchBarState by remember { mutableStateOf(false) }

    BackHandler() {
        searchBarState = false
        searchText = ""
    }
    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {
                    if (searchBarState.not()) {
                        Text(
                            "Notes",
                            fontSize = 36.sp,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    } else {
                        (SearchField(
                            searchText = searchText,
                            onValueChange = { value ->
                                searchText = value
                            },
                            onCancelClick = {
                                searchText = ""
                            }))
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.DarkGray
                ),


                actions = {
                    IconButton(onClick = {
                        searchBarState = searchBarState.not()
                        searchText = ""
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Search Bar",
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

        ) { innerPadding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
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
