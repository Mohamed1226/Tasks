package com.example.tasks.Presentation.dashboard

import com.example.tasks.core.enums.TaskStatus

sealed class TaskEvent {
    data class OnTitleChange(val title: String) : TaskEvent()
    data class OnDescriptionChange(val description: String) : TaskEvent()
    data class OnDateChange(val millis: Long?) : TaskEvent()
    data class OnStatusChange(val status: TaskStatus) : TaskEvent()
    data class OnColorChange(val color: List<Int>) : TaskEvent()
    data object DeleteTask : TaskEvent()
    data object SaveTask : TaskEvent()
    data class EditTask(val id : Int) : TaskEvent()

}