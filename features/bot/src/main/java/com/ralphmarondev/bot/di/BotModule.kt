package com.ralphmarondev.bot.di

import com.ralphmarondev.bot.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val botModule = module {
    viewModelOf(::HomeViewModel)
}