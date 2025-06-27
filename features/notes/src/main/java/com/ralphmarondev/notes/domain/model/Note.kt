package com.ralphmarondev.notes.domain.model

data class Note(
    val id: Long = 0,
    val title: String,
    val caption: String,
    val lastModified: Long = System.currentTimeMillis(),
    val isDeleted: Boolean = false
)
