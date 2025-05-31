package com.example.leveluptasks.ui.addtask

import androidx.lifecycle.viewModelScope
import com.example.leveluptasks.data.repository.TaskRepository
import com.example.leveluptasks.ui.BaseTaskDetailsViewModel
import com.example.leveluptasks.ui.state.TaskDetailsUiState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTaskViewModel(
    taskRepository: TaskRepository
) : BaseTaskDetailsViewModel(taskRepository) {

    fun saveTask() {
        viewModelScope.launch {
            taskRepository.addTask(_taskDetailsUiState.value.task)
            _taskDetailsUiState.update {
                it.copy(isTaskSaved = true)
            }
        }
    }

    fun clearAddTaskUiState(){
        _taskDetailsUiState.value = TaskDetailsUiState()
    }


}