package com.example.tasks.Presentation.task_details

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.Presentation.dashboard.SnackbarEvent
import com.example.tasks.Presentation.dashboard.TaskEvent
import com.example.tasks.core.repos.task.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(
    val taskRepo: TaskRepo,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val state = MutableStateFlow(TaskDetailsState())
    private val _snackbarEventFlow = MutableSharedFlow<SnackbarEvent>()


    init {
        val taskId: Int? = savedStateHandle["id"]
        if (taskId != null) {
            fetchTask(taskId)
        }
    }

    private fun fetchTask(taskId: Int) {
        viewModelScope.launch {
            val existingTask = taskRepo.getTaskById(taskId)
            if (existingTask != null) {
                state.update {
                    it.copy(
                        task = existingTask,
                        taskId = existingTask.taskId,
                        status = existingTask.status,
                        description = existingTask.description,
                        title = existingTask.title,
                        color = existingTask.color,
                        dueDate = existingTask.dueDate

                    )
                }

            }
        }


    }

    fun onEvent(event: TaskDetailsEvent) {
        when (event) {
            is TaskDetailsEvent.DeleteTask -> deleteTask()
            is TaskDetailsEvent.EditTask -> editTask(event.id)
            is TaskDetailsEvent.OnColorChange -> state.update {
                it.copy(color = event.color)
            }

            is TaskDetailsEvent.OnDateChange -> state.update {
                it.copy(dueDate = event.millis)
            }

            is TaskDetailsEvent.OnDescriptionChange -> state.update {
                it.copy(description = event.description)
            }

            is TaskDetailsEvent.OnStatusChange -> state.update {
                it.copy(status = event.status)
            }

            is TaskDetailsEvent.OnTitleChange -> state.update {
                it.copy(title = event.title)
            }
        }
    }

    private fun deleteTask() {
        viewModelScope.launch {
            try {
                val currentTaskId = state.value.taskId
                if (currentTaskId != null) {
                    withContext(Dispatchers.IO) {
                        taskRepo.deleteTask(taskId = currentTaskId)
                    }
                    _snackbarEventFlow.emit(
                        SnackbarEvent.ShowSnackbar(message = "Task deleted successfully")
                    )
                    _snackbarEventFlow.emit(SnackbarEvent.NavigateUp)
                } else {
                    _snackbarEventFlow.emit(
                        SnackbarEvent.ShowSnackbar(message = "No Task to delete")
                    )
                }
            } catch (e: Exception) {
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        message = "Couldn't delete task. ${e.message}",
                        duration = SnackbarDuration.Long
                    )
                )
            }
        }
    }


    private fun editTask(taskId: Int) {
        Log.d("taskId", taskId.toString())
        Log.d("taskId", state.value.status.name)

        viewModelScope.launch {
            val state = state.value

            try {
                val existingTask = taskRepo.getTaskById(taskId)
                if (existingTask != null) {
                    val updatedTask = existingTask.copy(
                        title = state.title,
                        description = state.description,
                        status = state.status,
                        color = state.color,
                        dueDate = state.dueDate!!
                    )
                    val rowsUpdated = taskRepo.editTask(updatedTask)
                    if (rowsUpdated > 0) {
                        Log.d("TaskDao", "Task updated successfully.")
                    } else {
                        Log.e("TaskDao", "Task update failed. No matching row found.")
                    }
                } else {
                    Log.e("TaskDao", "Task with ID: $taskId not found.")
                }


                withContext(Dispatchers.Main) {
                    _snackbarEventFlow.emit(
                        SnackbarEvent.ShowSnackbar(message = "Task Saved Successfully")
                    )
                    _snackbarEventFlow.emit(SnackbarEvent.NavigateUp)
                }
                _snackbarEventFlow.emit(SnackbarEvent.NavigateUp)
            } catch (e: Exception) {

                // Switch back to Main dispatcher to update the UI
                withContext(Dispatchers.Main) {
                    _snackbarEventFlow.emit(
                        SnackbarEvent.ShowSnackbar(
                            message = "Couldn't save task. ${e.message}",
                            duration = SnackbarDuration.Long
                        )
                    )
                    _snackbarEventFlow.emit(SnackbarEvent.NavigateUp)
                }
            }

        }

    }

}