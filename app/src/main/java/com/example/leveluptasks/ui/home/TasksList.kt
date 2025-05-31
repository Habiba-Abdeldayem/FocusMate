package com.example.leveluptasks.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leveluptasks.data.model.Task
import com.example.leveluptasks.ui.components.AlertDialogue

@Composable
fun TasksList(
    tasksViewModel: TasksViewModel,
    onAddTaskClick: () -> Unit,
    onTaskEditClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val taskUiState by tasksViewModel.uiState.collectAsState()
    var taskBeingEdited by rememberSaveable { mutableStateOf<Task?>(null) }
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(taskUiState.tasksList, key = { it.taskId }) { task ->
            TaskCard(
                task = task, viewModel = tasksViewModel,
                onEdit = { onTaskEditClick(it.taskId) }
            )
        }
        item{
            Button(onClick = onAddTaskClick) { Text(text = "add task") }
        }

    }

    taskBeingEdited?.let {
        AlertDialogue(
            task = it,
            allTasksViewModel = tasksViewModel,
            onSave = { taskBeingEdited = null },
            onCancel = { taskBeingEdited = null },
            onDismissRequest = { taskBeingEdited = null },
        )
    }

}

@Composable
fun TaskCard(
    task: Task,
    viewModel: TasksViewModel,
    onEdit: (Task) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(modifier = modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = task.name, modifier = Modifier.weight(1f))
//            Spacer(Modifier.width(40.dp))
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { viewModel.toggleTask(task) },

                )
            IconButton(onClick = { onEdit(task) }) {
                Icon(imageVector = Icons.Filled.Edit, contentDescription = "Edit Task")
            }
            IconButton(onClick = { viewModel.removeTask(task = task) }) {
                Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TasksListPreview() {
//    val viewModel: TasksViewModel = viewModel()
//    TasksList(viewModel)
}