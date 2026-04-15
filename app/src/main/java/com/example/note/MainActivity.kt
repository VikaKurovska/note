package com.example.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesScreen()
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotesScreen() {
    val isAdded = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = { Text("Notes", fontSize = 36.sp) },
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
                    IconButton(onClick = { }) {
                        Icon(
                            painter = painterResource(id = R.drawable.img_sort),
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
                onClick = { /* дія */ },
                shape = CircleShape,
                containerColor = Color(0xFFFFB74D),
                modifier = Modifier.size(70.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.img_plus),
                    modifier = Modifier.size(28.dp),
                    contentDescription = "Додати нотатку",
                )
            }
            Spacer(modifier = Modifier.width(90.dp))
        },
        floatingActionButtonPosition = FabPosition.End ,

    ) { innerPadding ->
        // Основний контент екрану
        Box(modifier = Modifier.padding(innerPadding)) {
            // Тут будуть твої нотатки
        }
    }
}
