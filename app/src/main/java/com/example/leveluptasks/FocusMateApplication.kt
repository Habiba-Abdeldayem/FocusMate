package com.example.leveluptasks

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.leveluptasks.data.repository.SettingsRepository

// The name of file that contains the datastore
private const val DARK_MODE_PREFERENCES_NAME = "settings_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = DARK_MODE_PREFERENCES_NAME
)
class FocusMateApplication:Application() {
    lateinit var settingsRepository: SettingsRepository

    override fun onCreate() {
        super.onCreate()
        settingsRepository = SettingsRepository(dataStore)
    }
}