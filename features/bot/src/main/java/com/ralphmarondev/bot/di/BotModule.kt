package com.ralphmarondev.bot.di

import com.google.ai.client.generativeai.GenerativeModel
import com.ralphmarondev.bot.BuildConfig
import com.ralphmarondev.bot.data.repository.BotRepositoryImpl
import com.ralphmarondev.bot.domain.repository.BotRepository
import com.ralphmarondev.bot.domain.usecase.CreateMessageUseCase
import com.ralphmarondev.bot.domain.usecase.GenerateResponseUseCase
import com.ralphmarondev.bot.domain.usecase.GetAllMessageUseCase
import com.ralphmarondev.bot.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val botModule = module {
    single<String>(qualifier = named("GEMINI_API_KEY")) { BuildConfig.GEMINI_API_KEY }
    single {
        GenerativeModel(
            modelName = "gemini-2.5-flash",
            apiKey = get(named("GEMINI_API_KEY"))
        )
    }

    singleOf(::BotRepositoryImpl).bind<BotRepository>()

    factoryOf(::CreateMessageUseCase)
    factoryOf(::GetAllMessageUseCase)
    factoryOf(::GenerateResponseUseCase)

    viewModelOf(::HomeViewModel)
}