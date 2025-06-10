package com.ralphmarondev.maron_os.launcher.di

import com.ralphmarondev.maron_os.launcher.presentation.LauncherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val launcherModule = module {
    viewModelOf(::LauncherViewModel)
}