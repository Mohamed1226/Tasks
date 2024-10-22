package com.example.tasks.core.di

import android.app.Application
import androidx.room.Room
import com.example.tasks.core.database.AppDataBase
import com.example.tasks.core.repos.task.TaskRepo
import com.example.tasks.data.task.local_data_base.TaskDao
import com.example.tasks.data.task.local_data_base.repository.TaskRepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerDatabase(application: Application): AppDataBase {
        return Room.databaseBuilder(
            application,
            AppDataBase::class.java,
            "Tasks.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providerTaskDao(dataBase: AppDataBase): TaskDao {
        return dataBase.taskDao()
    }

    @Provides
    @Singleton
    fun providerTaskRepo(taskDao: TaskDao): TaskRepo {
        return TaskRepoImp(taskDao)
    }
}