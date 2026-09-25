package com.example.note.presentation.main_notes_screen.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.note.data.entity.Note
import com.example.note.presentation.components.NoteGridCard
import com.example.note.presentation.components.NoteListCard


@Composable
fun NotesList(
    notes: List<Note>,
    mode: NotesListModes,
    onNoteClick: (Int) -> Unit,
    onLongClick: (Int) -> Unit
) {
    if (mode == NotesListModes.GRID) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalItemSpacing = 12.dp,
        ) {
            items(
                items = notes,
                key = { it.id },
            ) { note ->
                NoteGridCard(note = note, onClick = onNoteClick, onLongClick = onLongClick)
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(
                items = notes,
                key = { it.id },
            ) { note ->
                NoteListCard(note = note, onClick = onNoteClick, onLongClick = onLongClick)
            }
        }
    }
}
