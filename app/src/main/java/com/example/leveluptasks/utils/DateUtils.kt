package com.example.leveluptasks.utils

import androidx.compose.ui.res.stringResource
import com.example.leveluptasks.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    fun formatTaskDetailsDate(timestamp: Long?): String? {
        return if(timestamp == null) {
            null
        } else {
            val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            sdf.format(Date(timestamp))
        }
    }
}