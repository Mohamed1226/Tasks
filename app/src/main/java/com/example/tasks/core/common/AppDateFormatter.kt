package com.example.tasks.core.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppDateFormatter {

    fun convertMillisToDate(millis: Long?): String? {
        if(millis == null) return  null
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(Date(millis))
    }
}