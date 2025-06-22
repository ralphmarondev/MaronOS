package com.ralphmarondev.notes.presentation.note_list

import androidx.lifecycle.ViewModel
import com.ralphmarondev.notes.domain.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteListViewModel : ViewModel() {

    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()


    init {
        _notes.value += Note(
            id = 1,
            title = "Hello",
            caption = "World"
        )
        _notes.value += Note(
            id = 2,
            title = "Hi",
            caption = "Kate"
        )
        _notes.value += Note(
            id = 3,
            title = "Hello",
            caption = "Ralph"
        )
    }
}