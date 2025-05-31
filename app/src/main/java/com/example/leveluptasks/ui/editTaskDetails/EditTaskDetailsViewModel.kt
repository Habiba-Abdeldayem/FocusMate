package com.example.leveluptasks.ui.editTaskDetails

import androidx.lifecycle.viewModelScope
import com.example.leveluptasks.data.model.Task
import com.example.leveluptasks.data.repository.TaskRepository
import com.example.leveluptasks.ui.BaseTaskDetailsViewModel
import com.example.leveluptasks.ui.state.TaskDetailsUiState
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditTaskDetailsViewModel(taskRepository: TaskRepository) :
    BaseTaskDetailsViewModel(taskRepository) {

    fun loadTask(taskId: String) {
        viewModelScope.launch {
            taskRepository.getTask(taskId).let{task ->
                _taskDetailsUiState.update { it.copy(
                    Task(
                        taskId = task.value?.taskId ?: "",
                        name = task.value?.name ?: "",
                        isDone = task.value?.isDone == true,
                        description = task.value?.description ?: "",
                        dueDate = task.value?.dueDate ?: 0
                    )
                ) }
            }
        }
    }

    fun saveTask() {
        viewModelScope.launch {
            val taskToSave = _taskDetailsUiState.value.task
            taskRepository.updateTask(taskToSave)
            _taskDetailsUiState.update {
                it.copy(isTaskSaved = true)
            }
        }
    }

    fun clearEditTaskUiState() {
        _taskDetailsUiState.value = TaskDetailsUiState()
    }
}