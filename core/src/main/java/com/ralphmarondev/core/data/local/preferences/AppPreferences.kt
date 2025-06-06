package com.ralphmarondev.core.data.local.preferences

import android.content.Context
import androidx.core.content.edit

class AppPreferences(
    private val context: Context
) {
    companion object {
        private const val PREFERENCES_NAME = "maron_os_preferences"
        private const val FIRST_LAUNCH = "first_launch"
        private const val DARK_THEME = "dark_theme"
    }

    private val sharedPreferences = context.getSharedPreferences(
        PREFERENCES_NAME,
        Context.MODE_PRIVATE
    )

    fun getIsFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true)
    }

    fun setIsFirstLaunch(value: Boolean = false) {
        sharedPreferences.edit { putBoolean(FIRST_LAUNCH, value) }
    }

    fun getIsDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(DARK_THEME, false)
    }

    fun setIsDarkTheme(value: Boolean = !getIsDarkTheme()) {
        sharedPreferences.edit { putBoolean(DARK_THEME, value) }
    }
}