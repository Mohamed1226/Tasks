package com.example.tasks.Presentation.task_details

import com.example.tasks.core.enums.TaskStatus

sealed class TaskDetailsEvent {
    data class OnTitleChange(val title: String) : TaskDetailsEvent()
    data class OnDescriptionChange(val description: String) : TaskDetailsEvent()
    data class OnDateChange(val millis: Long?) : TaskDetailsEvent()
    data class OnStatusChange(val status: TaskStatus) : TaskDetailsEvent()
    data class OnColorChange(val color: List<Int>) : TaskDetailsEvent()
    data object DeleteTask : TaskDetailsEvent()
    data class EditTask(val id : Int) : TaskDetailsEvent()

}