package com.example.leveluptasks.ui.addtask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.leveluptasks.R
import com.example.leveluptasks.ui.home.TasksViewModel

@Composable
fun AddTaskScreen(
    tasksViewModel: TasksViewModel,
    onTaskAdded: () -> Unit,
    navController: NavController
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        TextField(
            placeholder = { stringResource(R.string.task_name) },
            value = text,
            onValueChange = { text = it },
        )
        Button(
            onClick = {
                tasksViewModel.addTask(text)
                onTaskAdded()
            },
            modifier = Modifier.padding(8.dp)

        ) { Text(stringResource(R.string.add_task)) }
    }
}


@Preview(showBackground = true)
@Composable
fun NewTaskPreview() {
//    NewTask()
}