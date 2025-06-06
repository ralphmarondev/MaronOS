package com.ralphmarondev.maron_os

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ralphmarondev.core.theme.ThemeProvider
import com.ralphmarondev.core.theme.ThemeState
import com.ralphmarondev.maron_os.navigation.AppNavigation
import com.ralphmarondev.maron_os.ui.theme.MaronOSTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val themeState: ThemeState by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThemeProvider(themeState = themeState) {
                MaronOSTheme(darkTheme = themeState.darkTheme.value) {
                    AppNavigation()
                }
            }
        }
    }
}
