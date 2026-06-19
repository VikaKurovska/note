package com.example.note.data.entity

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteId")
    val id: Int = 0,
    val title: String,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val color: Int
)
/*val notesList = listOf(
    Note(1, "Shopping ListjShopping ListjShopping ListjShopping ListjShopping ListjShopping ListjShopping ListjShopping ListjShopping Listj", color = Color(0xFFFFF9C4)), // Light Yellow
    Note(2, "Android Ideas", color = Color(0xFFE3F2FD)), // Light Blue
    Note(3, "Gym Plan", color = Color(0xFFF8BBD0)),    // Pink
    Note(4, "Books to Read", color = Color(0xFFE1BEE7)), // Lavender
    Note(5, "Project DeadlineProject DeadlineProject DeadlineProject DeadlineProject DeadlineProject Deadline", color = Color(0xFFC8E6C9)), // Light Green
    Note(6, "Grocery", color = Color(0xFFFFFDD0)),      // Cream
    Note(7, "Travel Goals", color = Color(0xFFB2EBF2)),  // Cyan
    Note(8, "Work Meeting", color = Color(0xFFFFE0B2)), // Orange-ish
    Note(9, "App Design", color = Color(0xFFD1C4E9)),   // Deep Purple
    Note(10, "Gift Ideas", color = Color(0xFFF0F4C3))   // Lime
)
 */
