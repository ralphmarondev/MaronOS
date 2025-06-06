package com.ralphmarondev.maron_os.navigation

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    data object Setup

    @Serializable
    data object Auth

    @Serializable
    data object Launcher
}