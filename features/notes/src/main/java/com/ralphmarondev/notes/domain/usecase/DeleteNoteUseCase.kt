package com.ralphmarondev.notes.domain.usecase

import com.ralphmarondev.domain.model.Result
import com.ralphmarondev.notes.domain.repository.NoteRepository

class DeleteNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(id: Long): Result {
        return try {
            repository.delete(id)
            Result(
                success = true,
                message = "Note deleted successfully."
            )
        } catch (e: Exception) {
            Result(
                success = false,
                message = "Deleting note failed. Error: ${e.message}"
            )
        }
    }
}