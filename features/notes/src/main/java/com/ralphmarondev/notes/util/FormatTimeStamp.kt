package com.ralphmarondev.notes.util

import java.text.SimpleDateFormat
import java.util.Locale

fun formatTimestamp(millis: Long): String {
    return try {
        val formatter = SimpleDateFormat("h:mma dd-MM-yyyy", Locale.getDefault())
        formatter.format(millis)
    } catch (e: Exception) {
        "Invalid time format."
    }
}