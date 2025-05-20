package com.example.leveluptasks.ui.home

import androidx.lifecycle.ViewModel
import com.example.leveluptasks.data.source.DataSource
import com.example.leveluptasks.data.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TasksViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(TasksUiState())
    val uiState: StateFlow<TasksUiState> = _uiState
    val tasksCount:Int
        get() = _uiState.value.tasksList.size

    init {
        _uiState.value = TasksUiState(DataSource.fakeTasks)
    }

// ⚠️ Important:
// In Jetpack Compose, the UI only recomposes when the state reference changes.
// So we avoid using mutable lists directly and instead create a new list using `.copy()`
// to trigger recomposition when updating the state (e.g., tasksList + newTask).
    fun addTask(name: String) {
        val newTask = Task(name=name, isDone =  false)
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

    fun editTaskInfo(updatedTask: Task, newTaskName:String) {
        val updatedList = _uiState.value.tasksList.map {
            if(it.taskId == updatedTask.taskId) updatedTask.copy(name = newTaskName)
            else it
        }

        _uiState.update {
            it.copy(tasksList = updatedList)
        }
    }

    fun getAllTasks() = _uiState.value.tasksList
}