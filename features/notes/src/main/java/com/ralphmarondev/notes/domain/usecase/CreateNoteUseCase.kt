package com.ralphmarondev.notes.domain.usecase

import com.ralphmarondev.domain.model.Result
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.repository.NoteRepository

class CreateNoteUseCase(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note): Result {
        if (note.title.isBlank()) {
            return Result(
                success = false,
                message = "Title cannot be empty."
            )
        }
        if (note.caption.isBlank()) {
            return Result(
                success = false,
                message = "Caption cannot be empty."
            )
        }

        return try {
            repository.create(note)
            Result(
                success = true,
                message = "Note created successfully."
            )
        } catch (e: Exception) {
            Result(
                success = false,
                message = "Creating note failed. Error: ${e.message}"
            )
        }
    }
}