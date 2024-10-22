package com.example.tasks.Presentation.dashboard

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.example.tasks.core.models.Task
import com.example.tasks.core.repos.task.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val taskRepo: TaskRepo) : ViewModel() {


    val tasks = listOf(
        Task(taskId = 342, title = "task 1", color = Task.colors[0].map { it.toArgb() }, description = "dsfs", dueDate = 234),
        Task(taskId = 342,title = "task 1", color = Task.colors[0].map { it.toArgb() }, description = "dsfs", dueDate = 234),
        Task(taskId = 342,title = "task 1", color = Task.colors[0].map { it.toArgb() }, description = "dsfs", dueDate = 234),
        Task(taskId = 342,title = "task 1", color = Task.colors[0].map { it.toArgb() }, description = "dsfs", dueDate = 234),
    )

}