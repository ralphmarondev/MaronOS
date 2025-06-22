package com.ralphmarondev.notes.presentation.new_note

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewNoteViewModel : ViewModel() {

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _caption = MutableStateFlow("")
    val caption = _caption.asStateFlow()


    fun onTitleValueChange(value: String) {
        _title.value = value
    }

    fun onCaptionValueChange(value: String) {
        _caption.value = value
    }
}