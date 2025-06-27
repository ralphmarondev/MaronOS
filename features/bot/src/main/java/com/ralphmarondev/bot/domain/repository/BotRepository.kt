package com.ralphmarondev.bot.domain.repository

import com.ralphmarondev.bot.domain.model.Message
import kotlinx.coroutines.flow.Flow

interface BotRepository {

    suspend fun create(message: Message)

    fun getAll(): Flow<List<Message>>

    suspend fun generateResponse(message: String, history: List<Message>): String
}