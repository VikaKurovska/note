package com.example.note.di

import android.content.Context
import androidx.room.Room
import com.example.note.data.database.NoteDatabase
import com.example.note.data.database.NoteDao // Перевір, як точно називається твій DAO і де лежить
import com.example.note.data.database.NoteDatabase.Companion.MIGRATION_1_2
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDao(database: NoteDatabase): NoteDao {
        return database.noteDao()
    }
    @Provides
    @Singleton
    fun provideNoteDatabase(
        @ApplicationContext context: Context
    ): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_database"
        )
            .addMigrations(MIGRATION_1_2) // Викликаємо з companion object
            .build()
    }
    }
