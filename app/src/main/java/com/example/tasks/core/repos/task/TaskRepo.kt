package com.example.tasks.core.repos.task


import com.example.tasks.core.models.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepo {
    suspend fun upsertTask(task: Task)

    suspend fun deleteTask(taskId: Int)


    suspend fun getTaskById(taskId: Int): Task?

    fun getAllTasks(): Flow<List<Task>>
}