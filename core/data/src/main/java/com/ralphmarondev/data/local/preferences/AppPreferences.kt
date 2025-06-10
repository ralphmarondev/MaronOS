package com.ralphmarondev.data.local.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "maron_os_preferences"
val Context.appDataStore by preferencesDataStore(name = DATASTORE_NAME)

class AppPreferences(
    private val context: Context
) {
    companion object {
        val KEY_DARK_MODE = booleanPreferencesKey("dark_mode")
        val KEY_USERNAME = stringPreferencesKey("username")
    }

    suspend fun setDarkModeEnabled(enabled: Boolean) {
        context.appDataStore.edit { prefs ->
            prefs[KEY_DARK_MODE] = enabled
        }
    }

    val darkModeEnabled: Flow<Boolean> = context.appDataStore.data
        .map { prefs -> prefs[KEY_DARK_MODE] == true }

    suspend fun setUsername(username: String) {
        context.appDataStore.edit { prefs ->
            prefs[KEY_USERNAME] = username
        }
    }

    val username: Flow<String> = context.appDataStore.data
        .map { prefs -> prefs[KEY_USERNAME] ?: "" }
}