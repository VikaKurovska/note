package com.example.note.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.note.data.entity.Note

@Database(entities = [Note::class], version = 2)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE notes ADD COLUMN isArchived INTEGER NOT NULL DEFAULT 0")
            }
        }
        @Volatile
            private var INSTANCE: NoteDatabase? = null
            fun getInstance(context: Context): NoteDatabase {
                synchronized(this) {
                    var instance = INSTANCE
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            NoteDatabase::class.java,
                            "notes_database"
                        )
                            .addMigrations(MIGRATION_1_2)
                            .build()

                        INSTANCE = instance
                    }
                    return instance
                }

            }
        }
    }
