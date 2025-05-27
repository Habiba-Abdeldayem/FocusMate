package com.example.leveluptasks.ui.state

interface TaskUiStateBase {
    val taskName: String
    val taskDescription: String
    val dueDate: Long?
    val isDone: Boolean
}