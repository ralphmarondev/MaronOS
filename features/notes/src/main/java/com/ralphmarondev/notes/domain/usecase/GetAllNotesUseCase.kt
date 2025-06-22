package com.ralphmarondev.notes.domain.usecase

import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(
    private val repository: NoteRepository
) {
    operator fun invoke(): Flow<List<Note>> {
        return repository.getAll()
    }
}