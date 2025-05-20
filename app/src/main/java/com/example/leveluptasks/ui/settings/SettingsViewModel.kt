package com.example.leveluptasks.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.leveluptasks.LevelUpApplication
import com.example.leveluptasks.data.repository.SettingsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SettingsUiState(
//    val userSettings: UserSettings = UserSettings(isDarkMode = false, onboardingSeen = false)
    val isDarkMode: Boolean = false,
    val onboardingSeen: Boolean = false
)

class SettingsViewModel(
    private val userSettingsRepository: SettingsRepository
) : ViewModel() {
    val uiState: StateFlow<SettingsUiState> =
        userSettingsRepository.isDarkMode.map { isDarkMode ->
            SettingsUiState(isDarkMode = isDarkMode)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = SettingsUiState()
            )

    fun switchDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            userSettingsRepository.saveModePreferences(isDarkMode)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as LevelUpApplication)
                SettingsViewModel(application.settingsRepository)
            }
        }
    }

}