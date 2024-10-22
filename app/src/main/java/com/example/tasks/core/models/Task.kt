package com.example.tasks.core.models

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.tasks.Presentation.theme.gradient1
import com.example.tasks.Presentation.theme.gradient2
import com.example.tasks.Presentation.theme.gradient3
import com.example.tasks.Presentation.theme.gradient4
import com.example.tasks.Presentation.theme.gradient5
import com.example.tasks.core.enums.TaskStatus

@Entity
data class Task(
    val title: String,
    val color: List<Color>,
    val description: String,
    val dueDate: Long,
    var status: TaskStatus = TaskStatus.Recent,
    @PrimaryKey(autoGenerate = true)
    val taskId: Int? = null


) {
    fun isCompleted(): Boolean = status == TaskStatus.Closed

    companion object  {
        var colors = listOf(gradient1, gradient2, gradient3, gradient4, gradient5)
    }
}

