package com.example.leveluptasks.ui.state

data class AddTaskUiState(
    override val taskName: String = "",
    override val taskDescription: String = "",
    override val dueDate: Long? = null,
    override val isDone: Boolean = false,
    val isTaskSaved: Boolean = false
): TaskUiStateBase
