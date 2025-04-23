package com.example.focusmate.viewmodel

import com.example.focusmate.model.Task

data class TaskUiState (
    val tasksList:List<Task> = emptyList()
)