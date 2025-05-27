package com.example.leveluptasks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import com.example.leveluptasks.data.repository.TaskRepositoryImpl
import com.example.leveluptasks.ui.TasksViewModelFactory
import com.example.leveluptasks.ui.addtask.AddTaskViewModel
import com.example.leveluptasks.ui.home.TasksViewModel
import com.example.leveluptasks.ui.settings.SettingsViewModel

//import com.example.focusmate.ui.theme.FocusMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val taskRepository = TaskRepositoryImpl()
            val factory = TasksViewModelFactory(taskRepository)
            val tasksViewModel: TasksViewModel = viewModel(factory = factory)
            val addTaskViewModel: AddTaskViewModel = viewModel(factory = factory)
            val settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory)
            val isDarkMode = settingsViewModel.uiState.collectAsState()
            AppTheme(darkTheme = isDarkMode.value.isDarkMode) {
                LevelUpTasksApp(
                    tasksViewModel = tasksViewModel,
                    addTaskViewModel = addTaskViewModel,
                    settingsViewModel = settingsViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {

    }
}