package com.ralphmarondev.setup

import kotlinx.serialization.Serializable

@Serializable
object Routes {

    @Serializable
    data object Welcome

    @Serializable
    data object InstallationType

    @Serializable
    data object AppChoices

    @Serializable
    data object ThemeChoices

    @Serializable
    data object Encryption

    @Serializable
    data object LoginDetails

    @Serializable
    data object Summary

    @Serializable
    data object Installing

    @Serializable
    data object Completed
}