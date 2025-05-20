package com.example.leveluptasks.ui.addtask

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.leveluptasks.ui.settings.SettingsViewModel
import com.example.leveluptasks.ui.home.AllTasksViewModel


@Composable
fun NewTask(allTasksViewModel: AllTasksViewModel) {
    var text by remember { mutableStateOf("") }

    val taskUiState = allTasksViewModel.uiState.collectAsState().value
    val settingsViewModel: SettingsViewModel = viewModel(factory = SettingsViewModel.Factory)
    val deletemesettingsstate = settingsViewModel.uiState.collectAsState().value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        TextField(placeholder = {Text("ss")},
            value = text,
            onValueChange = { text = it },
            )
        Button(
            onClick = {allTasksViewModel.addTask(text)},
            modifier = Modifier.padding(8.dp)

        ) {Text("Add Task") }
        Button(onClick = {settingsViewModel.switchDarkMode(!deletemesettingsstate.isDarkMode)}) { Text(text="Switch mode") }
        Text(text = "mode" + deletemesettingsstate.isDarkMode)
    }
}



@Preview(showBackground = true)
@Composable
fun NewTaskPreview() {
//    NewTask()
}