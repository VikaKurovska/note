package com.example.note.presentation.theme

import androidx.compose.ui.graphics.Color

object NoteColors {
    val palette = listOf(
        0xFFE6E6FA.toInt(),
        0xFFFFF9C4.toInt(),
        0xFFFFCDD2.toInt(),
        0xFFBBDEFB.toInt(),
        0xFFC8E6C9.toInt(),
        0xFFFFE0B2.toInt(),
    )

    val defaultColor = palette.first()
    val onCard = 0xFF1C1B1F.toInt()

    fun random(): Int = palette.random()
}

fun Int.toComposeColor(): Color = Color(this.toUInt().toLong())
