package com.example.focusmate.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.twotone.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.focusmate.model.Task
import com.example.focusmate.viewmodel.TasksViewModel


@Composable
fun NewTask() {
    var text by remember { mutableStateOf("Hello") }
    val tasksViewModel: TasksViewModel = viewModel()

    val taskUiState = tasksViewModel.uiState.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center)
    {
        TextField(
            value = text,
            onValueChange = { text = it },
            )
        Button(
            onClick = {tasksViewModel.addTask(text)},

        ) {Text("Add Task") }
        LazyColumn {
            items(taskUiState.tasksList){
                task -> TaskCard(task, viewModel = tasksViewModel)
            }
        }
    }
}

@Composable
fun TaskCard(task: Task,viewModel: TasksViewModel) {
    Card {
        Row{
            Text(text = task.name)
            Text(text = task.isDone.toString())
            IconButton(onClick = {viewModel.removeTask(task = task)}) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
            }
            IconButton(onClick = {viewModel.toggleTask(updatedTask = task)}) {
                Icon(imageVector = Icons.TwoTone.CheckCircle,null)
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun NewTaskPreview() {
    NewTask()
}