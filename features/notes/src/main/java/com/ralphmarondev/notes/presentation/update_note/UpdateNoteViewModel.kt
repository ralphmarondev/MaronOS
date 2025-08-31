package com.ralphmarondev.notes.presentation.update_note

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ralphmarondev.notes.domain.model.Note
import com.ralphmarondev.notes.domain.usecase.GetNoteByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UpdateNoteViewModel(
    private val noteId: Long,
    private val getNoteByIdUseCase: GetNoteByIdUseCase
) : ViewModel() {

    private val _note = MutableStateFlow<Note?>(null)
    val note = _note.asStateFlow()

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _caption = MutableStateFlow("")
    val caption = _caption.asStateFlow()

    private val _showNoteNotSavedDialog = MutableStateFlow(false)
    val showNoteNotSavedDialog = _showNoteNotSavedDialog.asStateFlow()

    private val _oldTitle = MutableStateFlow("")
    private val _oldCaption = MutableStateFlow("")


    init {
        viewModelScope.launch {
            val note = getNoteByIdUseCase(noteId)
            _note.value = note

            _title.value = note?.title ?: ""
            _caption.value = note?.caption ?: ""

            note?.title?.let {
                _oldTitle.value = it
            }
            note?.caption?.let {
                _oldCaption.value = it
            }
        }
    }

    fun onTitleValueChange(value: String) {
        _title.value = value
    }

    fun onCaptionValueChange(value: String) {
        _caption.value = value
    }

    fun onShowNoteNotSavedDialogChange(value: Boolean) {
        _showNoteNotSavedDialog.value = value
    }

    fun navigateBack(
        action: () -> Unit
    ) {
        val oldTitle = _oldTitle.value.trim()
        val oldCaption = _oldCaption.value.trim()
        val title = _title.value.trim()
        val caption = _caption.value.trim()

        if (oldTitle != title || oldCaption != caption) {
            onShowNoteNotSavedDialogChange(true)
        } else {
            action()
        }
    }

    fun save() {
        _oldTitle.value = _title.value
        _oldCaption.value = _caption.value

        Log.d("App", "Updating database.")
    }
}