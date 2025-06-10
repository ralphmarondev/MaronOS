package com.ralphmarondev.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.ralphmarondev.data.local.preferences.AppPreferences

class ThemeState internal constructor(
    val darkTheme: State<Boolean>,
    private val preferences: AppPreferences
) {
    suspend fun toggleTheme() {
        preferences.setDarkModeEnabled(!darkTheme.value)
    }
}

@Composable
fun rememberThemeState(preferences: AppPreferences): ThemeState {
    val darkThemeFlow = preferences.darkModeEnabled
    val darkThemeState = darkThemeFlow.collectAsState(false)

    return remember(preferences, darkThemeState.value) {
        ThemeState(darkThemeState, preferences)
    }
}