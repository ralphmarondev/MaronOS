package com.ralphmarondev.maron_os.navigation

import kotlinx.serialization.Serializable

@Serializable
object Routes {

    @Serializable
    data object Splash

    @Serializable
    data object Setup

    @Serializable
    data object Auth

    @Serializable
    data object Launcher

    @Serializable
    data object Settings

    @Serializable
    data object Browser

    @Serializable
    data object Notes

    @Serializable
    data object Camera

    @Serializable
    data object Gallery

    @Serializable
    data object Calendar

    @Serializable
    data object Bot

    @Serializable
    data object Quri
}