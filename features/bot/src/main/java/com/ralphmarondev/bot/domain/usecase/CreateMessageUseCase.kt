package com.ralphmarondev.bot.domain.usecase

import com.ralphmarondev.bot.domain.model.Message
import com.ralphmarondev.bot.domain.repository.BotRepository

class CreateMessageUseCase(
    private val botRepository: BotRepository
) {
    suspend operator fun invoke(message: Message) {
        botRepository.create(message)
    }
}