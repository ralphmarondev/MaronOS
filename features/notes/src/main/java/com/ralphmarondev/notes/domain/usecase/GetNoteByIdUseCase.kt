package com.ralphmarondev.notes.domain.usecase

import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.repository.NoteRepository

class GetNoteByIdUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Long): Note? {
        return repository.getById(id)
    }
}