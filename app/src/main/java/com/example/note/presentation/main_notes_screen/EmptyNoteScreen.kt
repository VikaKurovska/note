package com.example.note

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyNoteScreen(){
    Box(){
        Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, // Центрировать по вертикали
            horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.empty_note_icon),
                modifier = Modifier.size(207.dp, 209.dp),
                contentDescription = "Empty note image"

            )
            Text(text = "Create your first note !", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth() .padding(top = 12.dp))
        }
    }
}