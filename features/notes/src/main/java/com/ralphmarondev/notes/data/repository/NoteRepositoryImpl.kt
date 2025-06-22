package com.ralphmarondev.notes.data.repository

import com.ralphmarondev.data.local.database.dao.NoteDao
import com.ralphmarondev.data.local.database.entity.NoteEntity
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository {
    override suspend fun create(note: Note) {
        noteDao.create(
            note = NoteEntity(
                title = note.title,
                caption = note.caption
            )
        )
    }

    override suspend fun update(note: Note) {
        noteDao.update(
            note = NoteEntity(
                id = note.id,
                title = note.title,
                caption = note.caption,
                lastModified = note.lastModified,
                isDeleted = note.isDeleted
            )
        )
    }

    override suspend fun delete(id: Long) {
        noteDao.delete(id = id)
    }

    override suspend fun getById(id: Long): Note? {
        val note = noteDao.getById(id = id)
        return Note(
            id = note.id,
            title = note.title,
            caption = note.caption,
            lastModified = note.lastModified,
            isDeleted = note.isDeleted
        )
    }

    override fun getAll(): Flow<List<Note>> {
        val notes = noteDao.getAll()

        return notes.map { noteEntity ->
            noteEntity.map { entity ->
                Note(
                    id = entity.id,
                    title = entity.title,
                    caption = entity.caption,
                    lastModified = entity.lastModified,
                    isDeleted = entity.isDeleted
                )
            }
        }
    }
}