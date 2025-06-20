package com.ralphmarondev.maron_os

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ralphmarondev.data.local.preferences.AppPreferences
import com.ralphmarondev.maron_os.navigation.AppNavigation
import com.ralphmarondev.maron_os.navigation.Routes
import com.ralphmarondev.maron_os.ui.theme.MaronOSTheme
import com.ralphmarondev.presentation.theme.LocalThemeState
import com.ralphmarondev.presentation.theme.ThemeProvider
import kotlinx.coroutines.flow.first
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
                    var isFirstLaunch by remember { mutableStateOf<Boolean?>(null) }

                    LaunchedEffect(Unit) {
                        isFirstLaunch = preferences.isFirstLaunch.first()
                    }

                    if (isFirstLaunch == null) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    } else {
                        val startDestination = if (isFirstLaunch == true) {
                            Routes.Auth
                        } else {
                            Routes.Launcher
                        }
                        AppNavigation(startDestination = startDestination)
                    }
                }
            }
        }
    }
}