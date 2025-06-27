package com.ralphmarondev.bot.domain.usecase

import com.ralphmarondev.bot.domain.model.Message
import com.ralphmarondev.bot.domain.repository.BotRepository
import kotlinx.coroutines.flow.Flow

class GetAllMessageUseCase(
    private val botRepository: BotRepository
) {
    operator fun invoke(): Flow<List<Message>> {
        return botRepository.getAll()
    }
}