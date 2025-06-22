package com.ralphmarondev.notes.domain.usecase

import com.ralphmarondev.domain.model.Result
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.repository.NoteRepository

class UpdateNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note): Result {
        return try {
            repository.update(note)
            Result(
                success = true,
                message = "Note updated successfully."
            )
        } catch (e: Exception) {
            Result(
                success = false,
                message = "Update note failed. Error: ${e.message}"
            )
        }
    }
}