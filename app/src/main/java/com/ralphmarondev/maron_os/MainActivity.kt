package com.ralphmarondev.maron_os

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ralphmarondev.data.local.preferences.AppPreferences
import com.ralphmarondev.maron_os.launcher.presentation.LauncherScreen
import com.ralphmarondev.maron_os.ui.theme.MaronOSTheme
import com.ralphmarondev.presentation.theme.LocalThemeState
import com.ralphmarondev.presentation.theme.ThemeProvider
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val preferences: AppPreferences by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThemeProvider(preferences = preferences) {
                val themeState = LocalThemeState.current
                MaronOSTheme(darkTheme = themeState.darkTheme.value) {
                    LauncherScreen()
                }
            }
        }
    }
}