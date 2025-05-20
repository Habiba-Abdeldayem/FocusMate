package com.example.leveluptasks.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.AppTheme
import com.example.leveluptasks.ui.TasksList

@Composable
fun HomeScreen(
    viewModel: AllTasksViewModel = viewModel(),
    onAddTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tasks = viewModel.getAllTasks()
    Column(modifier = modifier) {
        TasksList(allTasksViewModel = viewModel)
//        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onAddTaskClick) { Text(text = "add task") }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        HomeScreen(onAddTaskClick = {})
    }
}