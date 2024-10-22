package com.example.tasks.data.task.local_data_base.repository

import com.example.tasks.core.models.Task
import com.example.tasks.core.repos.task.TaskRepo
import com.example.tasks.data.task.local_data_base.TaskDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepoImp @Inject constructor(private val taskDao: TaskDao) : TaskRepo {
    override suspend fun upsertTask(task: Task) {
         taskDao.upsertTask(task)
    }

    override suspend fun deleteTask(taskId: Int) {
         taskDao.deleteTask(taskId)
    }

    override suspend fun getTaskById(taskId: Int): Task? {
       return  taskDao.getTaskById(taskId)
    }

    override fun getAllTasks(): Flow<List<Task>> {
        return   taskDao.getAllTasks()
    }

    override suspend fun editTask(task: Task) {
        taskDao.editTask(task)
    }
}