package com.ralphmarondev.setup.navigation

import kotlinx.serialization.Serializable

object Routes {

    @Serializable
    data object Welcome

    @Serializable
    data object Theme

    @Serializable
    data object Notification

    @Serializable
    data object Account

    @Serializable
    data object Summary
}