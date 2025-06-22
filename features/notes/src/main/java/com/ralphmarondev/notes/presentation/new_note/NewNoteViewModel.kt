package com.ralphmarondev.notes.presentation.new_note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.domain.model.Result
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecase.CreateNoteUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewNoteViewModel(
    private val createNoteUseCase: CreateNoteUseCase
) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _caption = MutableStateFlow("")
    val caption = _caption.asStateFlow()

    private val _response = MutableStateFlow<Result?>(null)
    val response = _response.asStateFlow()


    fun onTitleValueChange(value: String) {
        _title.value = value
    }

    fun onCaptionValueChange(value: String) {
        _caption.value = value
    }

    fun resetResponse() {
        _response.value = null
    }

    fun create() {
        viewModelScope.launch {
            val note = Note(
                title = _title.value.trim(),
                caption = _caption.value.trim()
            )
            val result = createNoteUseCase(note)
            _response.value = result
        }
    }
}