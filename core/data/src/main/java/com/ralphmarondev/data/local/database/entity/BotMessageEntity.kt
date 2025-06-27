package com.ralphmarondev.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bot_message")
data class BotMessageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val message: String,
    val role: String,
    val timestamp: Long = System.currentTimeMillis()
)