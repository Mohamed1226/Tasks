package com.example.tasks.core.common

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class AppDateFormatter {

    fun convertMillisToDate(millis: Long?): String? {
        if(millis == null) return  null
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(Date(millis))
    }

    fun convertEpochMilliToReadableDate(epochMilli: Long): String {
        // Step 1: Convert milliseconds to Instant
        val instant = Instant.ofEpochMilli(epochMilli)

        // Step 2: Convert Instant to LocalDateTime with the desired time zone (e.g., system default)
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

        // Step 3: Format the date to a readable format
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss") // You can modify the pattern as needed
        return dateTime.format(formatter)
    }
}