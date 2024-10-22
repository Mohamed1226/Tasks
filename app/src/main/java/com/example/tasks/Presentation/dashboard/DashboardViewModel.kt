package com.example.tasks.Presentation.dashboard

import android.util.Log
import androidx.compose.material3.SnackbarDuration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasks.core.enums.TaskStatus
import com.example.tasks.core.models.Task
import com.example.tasks.core.repos.task.TaskRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val taskRepo: TaskRepo,
) : ViewModel() {


    private val _state = MutableStateFlow(TaskState())
    val state = combine(
        _state,
        taskRepo.getAllTasks()
    ) { state, tasks ->

        state.copy(
            tasks = tasks,
            recentTasks = tasks.filter { it.status == TaskStatus.Recent },
            closedTasks = tasks.filter { it.status == TaskStatus.Closed },
            upComingTasks = tasks.filter { it.status == TaskStatus.UpComing },
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = TaskState()
    )

    private val _snackbarEventFlow = MutableSharedFlow<SnackbarEvent>()


    fun onEvent(event: TaskEvent) {
        when (event) {
            is TaskEvent.OnTitleChange -> {
                _state.update {
                    it.copy(title = event.title)
                }
            }

            is TaskEvent.OnDescriptionChange -> {
                _state.update {
                    it.copy(description = event.description)
                }
            }

            is TaskEvent.OnDateChange -> {
                _state.update {
                    it.copy(dueDate = event.millis)
                }
            }

            is TaskEvent.OnStatusChange -> {
                _state.update {
                    it.copy(status = event.status)
                }
            }

            is TaskEvent.OnColorChange -> {
                _state.update {
                    it.copy(color = event.color)
                }
            }

            is TaskEvent.DeleteTask -> deleteTask()
            is TaskEvent.SaveTask -> saveTask()
            is TaskEvent.EditTask -> editTask(event.id)
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

    private fun saveTask() {
        viewModelScope.launch {
            val state = _state.value
            try {
                taskRepo.upsertTask(
                    task = Task(
                        title = state.title,
                        description = state.description,
                        dueDate = state.dueDate ?: Instant.now().toEpochMilli(),
                        color = state.color,
                        status = state.status,
                        taskId = state.taskId
                    )
                )
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(message = "Task Saved Successfully")
                )
                _snackbarEventFlow.emit(SnackbarEvent.NavigateUp)
            } catch (e: Exception) {
                _snackbarEventFlow.emit(
                    SnackbarEvent.ShowSnackbar(
                        message = "Couldn't save task. ${e.message}",
                        duration = SnackbarDuration.Long
                    )
                )
            }
        }
    }

    private fun editTask(taskId: Int) {
        Log.d("taskId", taskId.toString())
        Log.d("taskId", _state.value.status.name)

        viewModelScope.launch {
            val state = _state.value

            try {
                val existingTask = taskRepo.getTaskById(taskId)
                if (existingTask != null) {
                    val updatedTask = existingTask.copy(
                        title = state.title,
                        description = state.description,
                        status = state.status,
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
//                taskRepo.editTask(
//                    task = Task(
//                        title = state.title,
//                        description = state.description,
//                        dueDate = state.dueDate!!,
//                        color = state.color,
//                        status = state.status,
//                        taskId = taskId
//                    )
//                )

                // Switch back to Main dispatcher to update the UI
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


sealed class SnackbarEvent {
    data class ShowSnackbar(
        val message: String,
        val duration: SnackbarDuration = SnackbarDuration.Short
    ) : SnackbarEvent()

    data object NavigateUp : SnackbarEvent()
}
