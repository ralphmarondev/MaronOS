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
        val KEY_FIRST_LAUNCH = booleanPreferencesKey("first_launch")
        val ROOT_SETUP = booleanPreferencesKey("root_setup")

        val USER_USERNAME = stringPreferencesKey("user_username")
        val USER_FULL_NAME = stringPreferencesKey("user_full_name")
    }

    suspend fun setDarkModeEnabled(enabled: Boolean) {
        context.appDataStore.edit { prefs ->
            prefs[KEY_DARK_MODE] = enabled
        }
    }

    val darkModeEnabled: Flow<Boolean> = context.appDataStore.data
        .map { prefs -> prefs[KEY_DARK_MODE] == true }

    suspend fun setIsFirstLaunch(value: Boolean) {
        context.appDataStore.edit { prefs ->
            prefs[KEY_FIRST_LAUNCH] = value
        }
    }

    val isFirstLaunch: Flow<Boolean> = context.appDataStore.data
        .map { prefs -> prefs[KEY_FIRST_LAUNCH] != false }

    suspend fun setupRootUser(value: Boolean) {
        context.appDataStore.edit { prefs ->
            prefs[ROOT_SETUP] = value
        }
    }

    val isRootUserExists: Flow<Boolean> =
        context.appDataStore.data.map { prefs -> prefs[ROOT_SETUP] == true }


    suspend fun setUserUsername(username: String) {
        context.appDataStore.edit { it[USER_USERNAME] = username }
    }

    val userUsername: Flow<String> = context.appDataStore.data.map { it[USER_USERNAME] ?: "" }

    suspend fun setUserFullName(fullName: String) {
        context.appDataStore.edit { it[USER_FULL_NAME] = fullName }
    }

    val userFullName: Flow<String> = context.appDataStore.data.map { it[USER_FULL_NAME] ?: "" }
}