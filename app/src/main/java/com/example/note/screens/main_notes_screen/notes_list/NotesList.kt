package com.example.note.screens.main_notes_screen.notes_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.note.model.Note
import com.example.note.components.NoteGridCard
import com.example.note.components.NoteListCard


@Composable
fun NotesList(
    notes: List<Note>,          // Список самих нотаток
    mode: NotesListModes        // Режим: GRID або LIST
) {
    if (mode == NotesListModes.GRID) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalItemSpacing = 12.dp
        ) {
            items(notes) { note ->
                NoteGridCard(note = note)
            }
        }
    }
    else{
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(notes.size){ note ->
                val item = notes[note]
                NoteListCard(note= item)
            }
        }
    }
}
