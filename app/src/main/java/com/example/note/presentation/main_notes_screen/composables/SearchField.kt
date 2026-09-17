package com.example.note.presentation.main_notes_screen.composables

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
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
fun SearchField(
    searchText: String,
    onValueChange: (String) -> Unit,
    onCancelClick: () -> Unit
) {
    TextField(
        value = searchText,
        onValueChange = onValueChange,
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
            IconButton(onClick = onCancelClick) {
                Icon(
                    painter = painterResource(id = R.drawable.img_cancel),
                    contentDescription = "search"
                )
            }
        },
        colors = TextFieldDefaults.colors().copy()
    )
}


//            bottomBar = {
//                BottomAppBar {
//                    TextField(
//                        value = searchText,
//                        onValueChange = { searchText = it },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(horizontal = 12.dp),
//                        placeholder = { Text("Search...") },
//                        singleLine = true,
//                        shape = CircleShape,
//                        leadingIcon = {
//                            Icon(
//                                painter = painterResource(id = R.drawable.img_search),
//                                contentDescription = "search"
//                            )
//                        },
//                        trailingIcon = {
////додати умову
//                            IconButton(onClick = { searchText = "" }) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.img_cancel),
//                                    contentDescription = "search"
//                                )
//                            }
//                        }
//                    )
//                }
//            }