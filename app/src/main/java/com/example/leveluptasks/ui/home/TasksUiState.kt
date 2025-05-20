package com.example.leveluptasks.ui.home

import com.example.leveluptasks.data.model.Task

data class TasksUiState (
    val tasksList:List<Task> = emptyList()
)