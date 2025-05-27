package com.example.leveluptasks.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.leveluptasks.data.model.Task
import com.example.leveluptasks.ui.home.TasksViewModel

@Composable
fun AlertDialogue(
    onDismissRequest: () -> Unit,
    task: Task,
    allTasksViewModel: TasksViewModel,
    onSave: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var taskName by remember { mutableStateOf(task.name) }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
        Button(
            onClick = {
                onSave()
                allTasksViewModel.editTaskInfo(updatedTask = task)
            }
        ) {
            Text(text = "Save")
        }

    },
        dismissButton = {
            FilledTonalButton(onClick = {
                onCancel()
            }) {
                Text(text = "Cancel")
            }

        }, title = {
        Row {
            Text(text = "Edit Task Info")
        }
    }, text = {
        Column {
            TextField(value = taskName, onValueChange = { taskName = it })
        }
    }, modifier = Modifier.height(250.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
//    AlertDialogue(onDismissRequest = {})
}