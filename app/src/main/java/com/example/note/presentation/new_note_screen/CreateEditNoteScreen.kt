package com.example.note.presentation.new_note_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.note.R
import com.example.note.presentation.viewmodel.NewNoteVM

@Composable
fun CreateEditNoteScreen(
    id: Int = -1,
    onBackClick: () -> Unit = {},
    viewModel: NewNoteVM = hiltViewModel()
) {
    // id — це параметр екрана CreateEditNoteScreen(id: Int = -1, ...)
    LaunchedEffect(id) {
        viewModel.getNoteFromDB(id) // Викликаємо твій метод з NewNoteVM
    }

    // 2. Підписуємося на стан з ViewModel (автоматичне перемалювання)
    val titleText by viewModel.title.collectAsState()
    val contentText by viewModel.content.collectAsState()


    Scaffold(
        topBar = {
            CreateNoteTopBar(
                isEditMode = (id != -1),
                onBackClick = {
                    viewModel.saveOnBack() // Гарантоване збереження при виході
                    onBackClick()
                },
                onSaveClick = {
                    viewModel.saveOnBack() // Примусове збереження
                    onBackClick()
                },
                onDeleteClick = {
                    if (id != -1) {
                        viewModel.deleteNote(id)
                    }
                    onBackClick()
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Column(modifier = Modifier.imePadding()) {
                NoteTitleInput(
                    text = titleText,
                    onTextChange = viewModel::onTitleChange
                )
                CreateNoteText(
                    text = contentText,
                    onTextChange = viewModel::onContentChange
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteTopBar(
    isEditMode: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.img_back_screen),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Повернутись назад",
                    tint = colorResource(id = R.color.WarmYellow)
                )
            }
        },
        title = {
            Text(if (isEditMode) "Edit Note" else "Add Note")
        },
        actions = {
            if (isEditMode) {
                IconButton(onClick = onDeleteClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.img_more_new_screen),
                        modifier = Modifier.size(32.dp),
                        contentDescription = "Видалити / Більше"
                    )
                }
            }
            IconButton(
                onClick = onSaveClick,
                shape = CircleShape,
                colors = IconButtonDefaults.iconButtonColors().copy(
                    containerColor = colorResource(R.color.WarmYellow)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_check),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "Збереження",
                    tint = Color.White
                )
            }
        }
    )
}


@Composable
fun NoteTitleInput(text: String, onTextChange: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text("Title", fontSize = 24.sp) },
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


@Composable
fun CreateNoteText(text: String, onTextChange: (String) -> Unit) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text("Type something...", fontSize = 18.sp) },
        textStyle = LocalTextStyle.current.copy(fontSize = 18.sp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}