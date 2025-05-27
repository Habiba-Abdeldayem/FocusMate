package com.example.leveluptasks.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.leveluptasks.R
import com.example.leveluptasks.ui.state.TaskUiStateBase

@Composable
fun TaskDetailsScreen(
    uiState: TaskUiStateBase,
    onTaskNameChange: (String) -> Unit,
    onTaskDescriptionChange: (String) -> Unit,
    onTaskDateChange: (Long?) -> Unit,
    onTaskSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        Column(modifier = modifier.fillMaxSize()) {
            OutlinedTextField(
                value = uiState.taskName,
                onValueChange = { onTaskNameChange(it) },
                placeholder = { Text(text = stringResource(R.string.task_name)) },
                textStyle = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            DatePickerField(
                dueDate = uiState.dueDate,
                onDateSelected = { onTaskDateChange(it) },
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = uiState.taskDescription,
                onValueChange = { onTaskDescriptionChange(it) },
                placeholder = { Text(text = stringResource(R.string.task_description)) },
                textStyle = MaterialTheme.typography.bodyLarge,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.weight(1f).fillMaxWidth(),

            )

        }

        Button(
            onClick = {onTaskSave()},
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Text(text = stringResource(R.string.save_button))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TaskDetailsScreenPreview() {
//    TaskDetailsScreen("Task one", "Task Desc", "dd/mm/yyyy")
}