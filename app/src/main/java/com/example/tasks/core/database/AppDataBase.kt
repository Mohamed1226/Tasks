package com.example.tasks.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.tasks.core.models.Task
import com.example.tasks.data.task.local_data_base.ColorListConverter
import com.example.tasks.data.task.local_data_base.TaskDao

@Database(
    version = 1,
    entities = [Task::class]
)

@TypeConverters(ColorListConverter::class)
abstract class AppDataBase : RoomDatabase() {
abstract fun taskDao() : TaskDao


}