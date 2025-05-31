package com.example.leveluptasks.ui

import androidx.lifecycle.ViewModel
import com.example.leveluptasks.data.repository.TaskRepository
import com.example.leveluptasks.ui.state.TaskDetailsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

abstract class BaseTaskDetailsViewModel(
    protected val taskRepository: TaskRepository
): ViewModel() {
    protected val _taskDetailsUiState = MutableStateFlow(TaskDetailsUiState())
    val taskUiState: StateFlow<TaskDetailsUiState> = _taskDetailsUiState

    fun onTaskNameChange(newName: String) {
        _taskDetailsUiState.update { it.copy(task = it.task.copy(name = newName)) }

    }
    fun onTaskDescriptionChange(newDescription: String) {
        _taskDetailsUiState.update { it.copy(task = it.task.copy(description = newDescription)) }
    }
    fun onTaskDateChange(newDueDate: Long?) {
        _taskDetailsUiState.update { it.copy(task = it.task.copy(dueDate = newDueDate))}
    }

}