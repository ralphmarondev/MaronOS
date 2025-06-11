package com.ralphmarondev.maron_os.launcher.domain.model

data class AppData(
    val iconRes: Int,
    val name: String,
    val onClick: () -> Unit
)
