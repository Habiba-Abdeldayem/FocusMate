package com.example.leveluptasks.ui.editTaskDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.leveluptasks.ui.components.TaskDetailsScreen

@Composable
fun EditTaskDetailsScreen(
    editTaskDetailsViewModel: EditTaskDetailsViewModel,
    onSaveTask: () -> Unit,
) {
    val editTaskUiState by editTaskDetailsViewModel.taskUiState.collectAsState()

    LaunchedEffect(editTaskUiState.isTaskSaved) {
        if (editTaskUiState.isTaskSaved) {
            editTaskDetailsViewModel.clearEditTaskUiState()
            onSaveTask()
        }
    }
    TaskDetailsScreen(
        uiState = editTaskUiState,
        onTaskNameChange = {editTaskDetailsViewModel.onTaskNameChange(it)},
        onTaskDescriptionChange = {editTaskDetailsViewModel.onTaskDescriptionChange(it)},
        onTaskDateChange = {editTaskDetailsViewModel.onTaskDateChange(it)},
        onTaskSave = {
            editTaskDetailsViewModel.saveTask()
                     },

    )
}