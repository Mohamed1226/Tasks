package com.example.tasks.data.task.local_data_base



import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.tasks.core.models.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Upsert
    suspend fun upsertTask(task: Task)

    @Query("DELETE FROM Task WHERE taskId = :taskId")
    suspend fun deleteTask(taskId: Int)


    @Query("SELECT * FROM Task WHERE taskId = :taskId")
    suspend fun getTaskById(taskId: Int): Task?

    @Query("SELECT * FROM Task")
    fun getAllTasks(): Flow<List<Task>>
    // Add the editTask method using @Update
    @Update
    suspend fun editTask(task: Task)
}