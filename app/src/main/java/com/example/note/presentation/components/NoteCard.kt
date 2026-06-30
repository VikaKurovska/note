package com.example.note.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.note.data.entity.Note
import com.example.note.utils.timeConvertion

@Composable
fun NoteGridCard(note: Note, onClick: (Int) -> Unit) {
    val formattedTime = timeConvertion(note.timestamp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(note.id) }),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors().copy()


    ) {
        Column(modifier = Modifier.padding()) {
            Text(
                text = note.title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp, vertical = 20.dp)
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = formattedTime,
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, bottom = 10.dp)
            )
        }
    }
}

@Composable
fun NoteListCard(note: Note, onClick: (Int) -> Unit) {
    val formattedTime = timeConvertion(note.timestamp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onClick(note.id) }),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors().copy()


    ) {
        Column(modifier = Modifier.padding()) {
            Text(
                text = note.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(horizontal = 26.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = formattedTime,
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, bottom = 10.dp)
            )
        }
    }
}




