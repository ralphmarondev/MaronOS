package com.ralphmarondev.bot.di

import com.google.ai.client.generativeai.GenerativeModel
import com.ralphmarondev.bot.BuildConfig
import com.ralphmarondev.bot.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val botModule = module {
    single<String>(qualifier = named("GEMINI_API_KEY")) { BuildConfig.GEMINI_API_KEY }
    single {
        GenerativeModel(
            modelName = "gemini-2.5-flash",
            apiKey = get(named("GEMINI_API_KEY"))
        )
    }

    viewModelOf(::HomeViewModel)
}