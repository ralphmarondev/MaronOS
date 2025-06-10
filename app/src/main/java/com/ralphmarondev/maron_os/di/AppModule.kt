package com.ralphmarondev.maron_os.di

import com.ralphmarondev.auth.di.authModule
import com.ralphmarondev.data.di.dataModule
import com.ralphmarondev.maron_os.launcher.di.launcherModule
import com.ralphmarondev.setup.di.setupModule

val appModule = listOf(
    dataModule,
    setupModule,
    authModule,
    launcherModule,
)