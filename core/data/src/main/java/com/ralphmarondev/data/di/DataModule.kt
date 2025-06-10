package com.ralphmarondev.data.di

import com.ralphmarondev.data.local.preferences.AppPreferences
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::AppPreferences)
}