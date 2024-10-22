package com.example.tasks.Presentation.task_details

import com.example.tasks.core.enums.TaskStatus
import com.example.tasks.core.models.Task

data class TaskDetailsState(
    val title: String = "",
    val description: String = "",
    val dueDate: Long? = null,
    val color: List<Int> = emptyList(),
    var status: TaskStatus = TaskStatus.Recent,
    val taskId: Int? = null,
    val task:Task? = null
)
