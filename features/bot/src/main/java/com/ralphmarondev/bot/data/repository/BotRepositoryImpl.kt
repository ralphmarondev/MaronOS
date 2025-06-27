package com.ralphmarondev.bot.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.ralphmarondev.bot.domain.model.Message
import com.ralphmarondev.bot.domain.repository.BotRepository
import com.ralphmarondev.data.local.database.dao.BotMessageDao
import com.ralphmarondev.data.local.database.entity.BotMessageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BotRepositoryImpl(
    private val botMessageDao: BotMessageDao,
    private val generativeModel: GenerativeModel
) : BotRepository {
    override suspend fun create(message: Message) {
        botMessageDao.create(
            botMessageEntity = BotMessageEntity(
                message = message.message,
                role = message.role
            )
        )
    }

    override fun getAll(): Flow<List<Message>> {
        return botMessageDao.getAll().map {
            it.map { message ->
                Message(
                    id = message.id,
                    message = message.message,
                    role = message.role,
                    timestamp = message.timestamp
                )
            }
        }
    }

    override suspend fun generateResponse(
        message: String,
        history: List<Message>
    ): String {
        val chat = generativeModel.startChat(
            history = history.map {
                content(if (it.role == "kate") "model" else it.role) { text(it.message) }
            }.toList()
        )

        return chat.sendMessage(message).text.toString()
    }
}