package com.example.leveluptasks.ui.components

import android.app.DatePickerDialog
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.leveluptasks.R
import com.example.leveluptasks.utils.DateUtils
import java.util.Calendar

@Composable
fun DatePickerField(
    dueDate: Long?,
    onDateSelected: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    val formattedDate = remember(dueDate) {
        dueDate?.let {
            DateUtils.formatTaskDetailsDate(dueDate)
        }
    }?: stringResource(R.string.select_due_date)
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                onDateSelected(calendar.timeInMillis)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
        )
    }

    Row(modifier = modifier) {
        Image(
            imageVector = Icons.Filled.CalendarMonth,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = formattedDate,
            modifier = Modifier
                .clickable {
                    if (dueDate != null) {
                        calendar.timeInMillis = dueDate
                    } else {
                        calendar.timeInMillis = System.currentTimeMillis()
                    }
                    datePickerDialog.show()
                }


        )
    }


}