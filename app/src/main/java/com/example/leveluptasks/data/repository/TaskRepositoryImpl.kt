package com.example.leveluptasks.data.repository

import com.example.leveluptasks.data.model.Task
import com.example.leveluptasks.data.source.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TaskRepositoryImpl: TaskRepository {
    private val dataSourceTasks = DataSource.fakeTasks
    private val tasks = MutableStateFlow<List<Task>>(dataSourceTasks)

    override suspend fun getAllTasks(): StateFlow<List<Task>> {
        return tasks
    }

    override suspend fun addTask(task: Task) {
        val currentList = tasks.value.toMutableList()
        currentList.add(task)
        tasks.value = currentList
    }

    override suspend fun removeTask(task: Task) {
        val currentList = tasks.value.toMutableList()
        currentList.remove(task)
        tasks.value = currentList
    }

    override suspend fun updateTask(updatedTask: Task) {
        val index = tasks.value.indexOfFirst { it.taskId == updatedTask.taskId }
        if(index != -1) {
            val currentList = tasks.value.toMutableList()
            currentList[index] = updatedTask
            tasks.value = currentList
        }
    }

}