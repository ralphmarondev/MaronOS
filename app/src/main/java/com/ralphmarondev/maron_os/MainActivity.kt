package com.ralphmarondev.maron_os

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.ralphmarondev.maron_os.ui.theme.MaronOSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var darkTheme by remember { mutableStateOf(false) }
            MaronOSTheme(darkTheme = darkTheme) {
                WelcomeScreen(
                    darkTheme = darkTheme,
                    toggleDarkTheme = { darkTheme = !darkTheme }
                )
            }
        }
    }
}
