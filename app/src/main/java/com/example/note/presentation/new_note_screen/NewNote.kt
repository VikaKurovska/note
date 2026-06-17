package com.example.note.presentation.new_note_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.R

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun CreateNote(onBackClick: () -> Unit = {}) {
    var titleText by remember { mutableStateOf("") }
    var contentText by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CreateNoteTopBar(onBackClick = onBackClick)
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.imePadding()) {
                NoteTitleInput(
                    text = titleText,
                    onTextChange = { titleText = it })
                CreateNoteText(
                    text = contentText,
                    onTextChange = {contentText = it})
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteTopBar(onBackClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteTitleInput(text: String, onTextChange: (String) -> Unit){
        TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text("Title", fontSize = 24.sp) }, // Підказка
            textStyle = LocalTextStyle.current.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteText(text: String, onTextChange: (String) -> Unit) {
    TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text("Type something...", fontSize = 18.sp) },
            textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
        )
    )
}