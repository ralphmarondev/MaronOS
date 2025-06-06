package com.ralphmarondev.maron_os.di

import com.ralphmarondev.core.di.coreModule
import com.ralphmarondev.setup.di.setupModule

val appModule = listOf(
    coreModule,
    setupModule
)