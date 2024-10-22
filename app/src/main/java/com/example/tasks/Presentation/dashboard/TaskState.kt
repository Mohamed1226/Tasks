package com.example.tasks.Presentation.dashboard


import com.example.tasks.core.enums.TaskStatus
import com.example.tasks.core.models.Task

data class TaskState(
    val title: String = "",
    val description: String = "",
    val dueDate: Long? = null,
    val color: List<Int> = emptyList(),
    var status: TaskStatus = TaskStatus.Recent,
    val taskId: Int? = null,
    val upComingTasks: List<Task> = emptyList(),
    val closedTasks: List<Task> = emptyList(),
    val recentTasks: List<Task> = emptyList(),
    val tasks: List<Task> = emptyList(),
)



