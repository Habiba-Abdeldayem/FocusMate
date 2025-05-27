package com.example.leveluptasks.ui.addtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leveluptasks.data.model.Task
import com.example.leveluptasks.data.repository.TaskRepository
import com.example.leveluptasks.ui.state.AddTaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTaskViewModel(
    private val taskRepository: TaskRepository
) : ViewModel() {
    private val _addTaskUiState = MutableStateFlow(AddTaskUiState())
    val addTaskUiState: StateFlow<AddTaskUiState> = _addTaskUiState

    fun saveTask() {
        viewModelScope.launch {
            val taskToSave = Task(
                name = _addTaskUiState.value.taskName,
                description = _addTaskUiState.value.taskName,
            )
            taskRepository.addTask(taskToSave)
            _addTaskUiState.update {
                it.copy(isTaskSaved = true)
            }
        }
    }

    fun onTaskNameChange(newName: String) {
        _addTaskUiState.update {
            it.copy(taskName = newName)
        }
    }

    fun onTaskDescriptionChange(newDescription: String) {
        _addTaskUiState.value = _addTaskUiState.value.copy(taskDescription = newDescription)
    }
    fun onTaskDueDateChange(timeStamp: Long?) {
        _addTaskUiState.value = _addTaskUiState.value.copy(dueDate = timeStamp)
    }

    fun clearAddTaskUiState(){
        _addTaskUiState.value = AddTaskUiState()
    }


}