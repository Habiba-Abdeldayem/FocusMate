package com.example.leveluptasks.data.model

import java.util.UUID

data class Task(
    val taskId:String = UUID.randomUUID().toString(),
    val name:String,
    val isDone:Boolean = false,
    val description: String = "",
    val dueDate: Long? = null
)
