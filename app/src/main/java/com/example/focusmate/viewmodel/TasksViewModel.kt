package com.example.focusmate.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.focusmate.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TasksViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: StateFlow<TaskUiState> = _uiState

    fun addTask(name: String) {
        val newTask = Task(name, false)
        _uiState.update {
            it.copy(tasksList = it.tasksList + newTask)
        }
    }

    fun removeTask(task: Task) {
        _uiState.update {
            it.copy(tasksList = it.tasksList - task)
        }
    }

    fun toggleTask(updatedTask:Task) {
        val updatedList = _uiState.value.tasksList.map {
            if(it.name == updatedTask.name) updatedTask.copy(isDone = !it.isDone)
            else it
        }

        _uiState.update {
            it.copy(tasksList = updatedList)
        }

    }
}