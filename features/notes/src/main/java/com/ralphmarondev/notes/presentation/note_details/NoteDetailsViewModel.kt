package com.ralphmarondev.notes.presentation.note_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecase.GetNoteByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteDetailsViewModel(
    private val noteId: Long,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) : ViewModel() {

    private val _note = MutableStateFlow<Note?>(null)
    val note = _note.asStateFlow()


    init {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId)
            _note.value = note
        }
    }
}