package com.ralphmarondev.bot.domain.usecase

import com.ralphmarondev.bot.domain.model.Message
import com.ralphmarondev.bot.domain.repository.BotRepository

class GenerateResponseUseCase(
    private val botRepository: BotRepository
) {
    suspend operator fun invoke(message: String, history: List<Message>): String {
        return botRepository.generateResponse(
            message = message,
            history = history
        )
    }
}