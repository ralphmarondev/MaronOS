package com.ralphmarondev.bot.domain.model

data class Message(
    val id: Long = 0,
    val message: String,
    val role: String,
    val timestamp: Long = System.currentTimeMillis()
)