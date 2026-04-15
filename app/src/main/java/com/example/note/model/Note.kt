package com.example.note.model

import androidx.compose.ui.graphics.Color

data class Note(
    val id: Int,
    val title: String,
    val date: String,
    val color: Color
)