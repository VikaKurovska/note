package com.example.note.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
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
import com.example.note.presentation.theme.NoteColors
import com.example.note.presentation.theme.toComposeColor
import com.example.note.utils.timeConvertion

@Composable
fun NoteGridCard(note: Note, onClick: (Int) -> Unit, onLongClick: (Int) -> Unit) {
    NoteCard(
        note = note,
        titleAlign = TextAlign.Start,
        onClick = onClick,
        onLongClick =  onLongClick,
    )
}

@Composable
fun NoteListCard(note: Note, onClick: (Int) -> Unit, onLongClick: (Int) -> Unit) {
    NoteCard(
        note = note,
        titleAlign = TextAlign.Center,
        onClick = onClick,
        onLongClick =  onLongClick,
    )
}

@Composable
private fun NoteCard(
    note: Note,
    titleAlign: TextAlign,
    onClick: (Int) -> Unit,
    onLongClick: (Int) -> Unit,
) {
    val formattedTime = timeConvertion(note.timestamp)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = { onClick(note.id) },
                onLongClick = {onLongClick(note.id)}),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = note.color.toComposeColor(),
            contentColor = NoteColors.onCard.toComposeColor(),
        ),
    ) {
        Column(modifier = Modifier.padding()) {
            Text(
                text = note.title,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = titleAlign,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 26.dp, vertical = 20.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = formattedTime,
                fontSize = 14.sp,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, bottom = 10.dp),
            )
        }
    }
}
