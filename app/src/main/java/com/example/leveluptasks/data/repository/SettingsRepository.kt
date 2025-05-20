package com.example.leveluptasks.data.repository

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class SettingsRepository (
    private val dataStore: DataStore<Preferences>
){
    val isDarkMode: Flow<Boolean> = dataStore.data
        .catch {
            if(it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
        preferences[IS_DARK_MODE] ?: false
    }
    private companion object {
        const val TAG = "SettingsPreferencesRepo"
        val IS_DARK_MODE = booleanPreferencesKey("is_dark_mode")
        val ONBOARDING_SEEN = booleanPreferencesKey("onboarding_seen")
    }

    suspend fun saveModePreferences(isDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_DARK_MODE] = isDarkMode
        }
    }
    suspend fun saveOnboardingStatus(onboardingSeen: Boolean) {
        dataStore.edit { preferences ->
            preferences[ONBOARDING_SEEN] = onboardingSeen
        }
    }
}