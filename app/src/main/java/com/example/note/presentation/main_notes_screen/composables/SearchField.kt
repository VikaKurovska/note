package com.example.note.presentation.main_notes_screen.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.note.R

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