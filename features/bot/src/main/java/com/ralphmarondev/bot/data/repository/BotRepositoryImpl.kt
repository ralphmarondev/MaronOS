package com.ralphmarondev.bot.data.repository

import com.ralphmarondev.bot.domain.model.Message
import com.ralphmarondev.bot.domain.repository.BotRepository
import com.ralphmarondev.data.local.database.dao.BotMessageDao
import com.ralphmarondev.data.local.database.entity.BotMessageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BotRepositoryImpl(
    private val botMessageDao: BotMessageDao
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
}