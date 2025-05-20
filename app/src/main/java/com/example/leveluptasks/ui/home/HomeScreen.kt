package com.example.leveluptasks.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.AppTheme

@Composable
fun HomeScreen(
    viewModel: TasksViewModel,
    onAddTaskClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TasksList(tasksViewModel = viewModel)
        Button(onClick = onAddTaskClick) { Text(text = "add task") }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    AppTheme {
//        HomeScreen(onAddTaskClick = {})
    }
}