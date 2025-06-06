package com.ralphmarondev.core.theme

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.ralphmarondev.core.data.local.preferences.AppPreferences

class ThemeState(
    private val preferences: AppPreferences
) {
    private val _darkTheme = mutableStateOf(preferences.getIsDarkTheme())
    val darkTheme: State<Boolean> get() = _darkTheme

    fun toggleDarkTheme() {
        _darkTheme.value = !_darkTheme.value
        preferences.setIsDarkTheme()
    }
}