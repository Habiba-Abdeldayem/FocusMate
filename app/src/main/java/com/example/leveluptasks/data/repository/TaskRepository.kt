package com.example.leveluptasks.data.repository

import com.example.leveluptasks.data.model.Task
import kotlinx.coroutines.flow.StateFlow

interface TaskRepository {
    suspend fun getAllTasks(): StateFlow<List<Task>>
    suspend fun addTask(task:Task)
    suspend fun removeTask(task:Task)
    suspend fun updateTask(updatedTask:Task)
}