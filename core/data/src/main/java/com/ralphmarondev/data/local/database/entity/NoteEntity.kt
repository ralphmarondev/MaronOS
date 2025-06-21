package com.ralphmarondev.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val caption: String,
    val lastModified: Long = System.currentTimeMillis(),
    val isDeleted: Boolean = false
)
