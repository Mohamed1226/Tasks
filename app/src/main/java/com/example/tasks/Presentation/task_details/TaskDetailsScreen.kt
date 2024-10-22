package com.example.tasks.Presentation.task_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tasks.Presentation.dashboard.DashboardViewModel
import com.example.tasks.Presentation.dashboard.TaskEvent
import com.example.tasks.Presentation.dashboard.TaskState
import com.example.tasks.Presentation.dashboard.componants.AddOrUpdateTaskDialog
import com.example.tasks.Presentation.dashboard.componants.DeleteTaskDialog
import com.example.tasks.Presentation.task_details.componant.AppTopAppBar
import com.example.tasks.Presentation.task_details.componant.TaskDetails
import com.example.tasks.core.models.Task
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

data class TaskId(val id: Int)


@Destination(navArgsDelegate = TaskId::class)
@Composable
fun TaskDetailsScreenRoute(navigator: DestinationsNavigator) {
    val viewmodel: TaskDetailsViewModel = hiltViewModel()
    val state by viewmodel.state.collectAsState()
    TaskDetailsScreen(
        onBackButtonClick = { navigator.navigateUp() },
        taskState = state,
        navigator = navigator,
        onEvent = viewmodel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TaskDetailsScreen(
    navigator: DestinationsNavigator,
    onBackButtonClick: () -> Unit, taskState: TaskDetailsState,
    onEvent: (TaskDetailsEvent) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var isEditTaskDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }


    DeleteTaskDialog(
        isOpen = isDeleteDialogOpen,
        onDismissRequest = { isDeleteDialogOpen = false },
        onConfirmButtonClick = {
            /// delete function
            onEvent(TaskDetailsEvent.DeleteTask)
            navigator.navigateUp()
            isDeleteDialogOpen = false
        }
    )
    AddOrUpdateTaskDialog(
        isOpen = isEditTaskDialogOpen,
        taskName = taskState.title,
        taskDescription = taskState.description,
        selectedColors = taskState.color.map { Color(it) },
        onTaskNameChange = { onEvent(TaskDetailsEvent.OnTitleChange(it)) },
        onTaskDescriptionChange = { onEvent(TaskDetailsEvent.OnDescriptionChange(it)) },
        onColorChange = { onEvent(TaskDetailsEvent.OnColorChange(it.map { it.toArgb() })) },
        onDismissRequest = { isEditTaskDialogOpen = false },
        onConfirmButtonClick = {
            onEvent(TaskDetailsEvent.EditTask(taskState.taskId!!))
            isEditTaskDialogOpen = false
        }
    )


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppTopAppBar(
                title = taskState.title,
                onBackButtonClick = onBackButtonClick,
                onDeleteButtonClick = {
                    isDeleteDialogOpen = true
                },
                onEditButtonClick = {
                    isEditTaskDialogOpen = true
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (taskState.task != null)
                item {
                    TaskDetails(
                        task = taskState.task,
                        modifier = Modifier,
                        onStatusClicked = { task ->
                            onEvent(TaskDetailsEvent.OnStatusChange(task))
                            onEvent(TaskDetailsEvent.EditTask(taskState.taskId!!))
                        })
                }
        }
    }


}


