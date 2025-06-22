package com.ralphmarondev.notes.domain.repository

import com.ralphmarondev.notes.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun create(note: Note)

    suspend fun update(note: Note)

    suspend fun delete(id: Long)

    suspend fun getById(id: Long): Note?

    fun getAll(): Flow<List<Note>>
}