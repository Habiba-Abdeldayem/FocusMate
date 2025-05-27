package com.example.leveluptasks.ui.state

data class EditTaskUiState(
    override val taskName: String = "",
    override val taskDescription: String = "",
    override val dueDate: Long? = null,
    override val isDone: Boolean = false,

): TaskUiStateBase
