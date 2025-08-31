package com.ralphmarondev.notes.presentation.note_details

import android.util.Log
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

    private val _showDeleteNoteDialog = MutableStateFlow(false)
    val showDeleteNoteDialog = _showDeleteNoteDialog.asStateFlow()


    init {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId)
            _note.value = note
        }
    }

    fun showDeleteNoteDialogChange(
        value: Boolean,
        action: () -> Unit = {}
    ) {
        _showDeleteNoteDialog.value = value
        if (value) {
            viewModelScope.launch {
                Log.d("App", "Deleting note...")
            }
        }
        Log.d("App", "Doing the action")
        action()
    }
}