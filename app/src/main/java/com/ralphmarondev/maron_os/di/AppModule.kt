package com.ralphmarondev.maron_os.di

import com.ralphmarondev.data.di.dataModule
import com.ralphmarondev.maron_os.launcher.di.launcherModule

val appModule = listOf(
    dataModule,
    launcherModule
)