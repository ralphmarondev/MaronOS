package com.ralphmarondev.gallery.presentation.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class HomeViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _images = MutableStateFlow<List<File>>(emptyList())
    val images = _images.asStateFlow()

    init {
        loadImages()
    }

    fun loadImages() {
        viewModelScope.launch {
            val imageFiles = getApplication<Application>().filesDir
                .listFiles { file -> file.extension == "jpg" }
                ?.sortedByDescending { it.lastModified() }
                ?.toList()
                ?: emptyList()

            _images.value = imageFiles
        }
    }

    fun deleteImage(file: File) {
        viewModelScope.launch {
            file.delete()
            loadImages()
        }
    }
}