package com.ralphmarondev.notes.presentation.note_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecase.GetAllNotesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteListViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()


    init {
        viewModelScope.launch {
            getAllNotesUseCase().collect { noteList ->
                _notes.value = noteList
            }
        }
    }
}