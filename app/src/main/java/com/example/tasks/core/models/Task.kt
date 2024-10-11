package com.example.tasks.core.models

import androidx.compose.ui.graphics.Color
import com.example.tasks.Presentation.theme.gradient1
import com.example.tasks.Presentation.theme.gradient2
import com.example.tasks.Presentation.theme.gradient3
import com.example.tasks.Presentation.theme.gradient4
import com.example.tasks.Presentation.theme.gradient5

data class Task(
    val title: String,
    val color : List<Color>
//    val description: String,
//    val dueDate: Long,
//    val priority: Int,
//    val relatedToSubject: String,
//    val isComplete: Boolean,
//    val taskSubjectId: Int,
//    val taskId: Int? = null


) {
    companion object TaskColors {
        val colors = listOf(gradient1,gradient2, gradient3, gradient4, gradient5)
    }
}