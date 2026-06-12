package com.example.note.new_note_creation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.note.R

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun CreateNote() {
    Scaffold(
        topBar = {
            CreateNoteTopBar()
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column() {}
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteTopBar() {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.img_back_screen),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Повернутись назад",
                    tint = colorResource(id = R.color.WarmYellow)
                )
            }
        },


        title = {
            Text("Add Note")
        },


        actions = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.img_more_new_screen),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Більше інформації"
                )
            }
        }
    )
}

@Composable
fun CreateNoteTitle(){

}
@Composable
fun CreateNoteText(){

}