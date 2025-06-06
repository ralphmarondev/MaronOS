package com.ralphmarondev.core.di

import com.ralphmarondev.core.data.local.preferences.AppPreferences
import com.ralphmarondev.core.theme.ThemeState
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreModule = module {
    factoryOf(::AppPreferences)
    factoryOf(::ThemeState)
}