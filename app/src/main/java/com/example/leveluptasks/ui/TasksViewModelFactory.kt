package com.example.leveluptasks.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.leveluptasks.data.repository.TaskRepository
import com.example.leveluptasks.ui.addtask.AddTaskViewModel
import com.example.leveluptasks.ui.home.TasksViewModel

class TasksViewModelFactory(private val repository: TaskRepository)
    : ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass:Class<T>):T {
        return when {
            modelClass.isAssignableFrom(TasksViewModel::class.java) ->
                TasksViewModel(repository) as T
            modelClass.isAssignableFrom(AddTaskViewModel::class.java) ->
                AddTaskViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
}