package com.example.leveluptasks.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leveluptasks.data.model.Task
import com.example.leveluptasks.data.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TasksViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TasksUiState())
    val uiState: StateFlow<TasksUiState> = _uiState
    val tasksCount: Int
        get() = _uiState.value.tasksList.size

    init {
        getAllTasks()
    }

    fun getAllTasks() {
        viewModelScope.launch {
            taskRepository.getAllTasks().collect { tasksList ->
                _uiState.value = _uiState.value.copy(tasksList = tasksList)
            }
        }
    }

    // ⚠️ Important:
// In Jetpack Compose, the UI only recomposes when the state reference changes.
// So we avoid using mutable lists directly and instead create a new list using `.copy()`
// to trigger recomposition when updating the state (e.g., tasksList + newTask).

    fun removeTask(task: Task) {
        viewModelScope.launch {
            taskRepository.removeTask(task)
            getAllTasks()
        }
    }

    fun toggleTask(updatedTask: Task) {
        viewModelScope.launch {
            taskRepository.updateTask(updatedTask.copy(isDone = !updatedTask.isDone))
            getAllTasks()
        }
    }

    fun editTaskInfo(updatedTask: Task) {
        viewModelScope.launch {
            taskRepository.updateTask(updatedTask)
        }
        getAllTasks()

        val updatedList = _uiState.value.tasksList.map {
            if (it.taskId == updatedTask.taskId) updatedTask
            else it
        }

        _uiState.update {
            it.copy(tasksList = updatedList)
        }
    }

}