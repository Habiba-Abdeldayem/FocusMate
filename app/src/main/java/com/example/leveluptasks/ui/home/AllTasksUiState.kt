package com.example.leveluptasks.ui.home

import com.example.leveluptasks.data.model.Task

data class AllTasksUiState (
    val tasksList:List<Task> = emptyList()
)