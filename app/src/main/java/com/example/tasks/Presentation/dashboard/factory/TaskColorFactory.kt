package com.example.tasks.Presentation.dashboard.factory

import androidx.compose.ui.graphics.Color
import com.example.tasks.Presentation.theme.Orange
import com.example.tasks.Presentation.theme.md_theme_dark_errorContainer
import com.example.tasks.Presentation.theme.md_theme_dark_primaryContainer
import com.example.tasks.core.enums.TaskStatus

class TaskColorFactory {


    companion object {
        @JvmStatic
        fun getStatusColor(
            status : TaskStatus) : Color {
            return when(status){
                TaskStatus.Recent -> md_theme_dark_primaryContainer
                TaskStatus.UpComing -> Orange
                TaskStatus.Closed -> md_theme_dark_errorContainer
            }
        }
    }
}