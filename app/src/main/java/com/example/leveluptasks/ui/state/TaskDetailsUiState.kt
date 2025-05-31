package com.example.leveluptasks.ui.state

import com.example.leveluptasks.data.model.Task

data class TaskDetailsUiState(
    override val task: Task = Task(),
    val isTaskSaved: Boolean = false
): TaskUiStateBase
