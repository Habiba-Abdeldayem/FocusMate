package com.example.leveluptasks.ui.addtask

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.leveluptasks.navigation.LevelUpTasksScreen
import com.example.leveluptasks.ui.components.TaskDetailsScreen

@Composable
fun AddTaskScreen(
    addTaskViewModel: AddTaskViewModel,
    onSaveTask: () -> Unit,
    navController: NavController
) {
    val addTaskUiState by addTaskViewModel.addTaskUiState.collectAsState()

    LaunchedEffect(addTaskUiState.isTaskSaved) {
        if(addTaskUiState.isTaskSaved) {
            navController.navigate(LevelUpTasksScreen.Home.name)
            addTaskViewModel.clearAddTaskUiState()
        }
    }
    TaskDetailsScreen(
        uiState = addTaskUiState,
        onTaskNameChange = {addTaskViewModel.onTaskNameChange(it)},
        onTaskDateChange = {
            addTaskViewModel.onTaskDueDateChange(it)
        },
        onTaskDescriptionChange = {
            addTaskViewModel.onTaskDescriptionChange(it)
        },
        onTaskSave = {
            addTaskViewModel.saveTask()
        }
    )
}


@Preview(showBackground = true)
@Composable
fun NewTaskPreview() {

}