package com.ralphmarondev.setup.di

import com.ralphmarondev.setup.SetupViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val setupModule = module {
    viewModelOf(::SetupViewModel)
}