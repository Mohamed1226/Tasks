package com.example.tasks.Presentation.dashboard

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tasks.Presentation.dashboard.componants.AddOrUpdateTaskDialog
import com.example.tasks.Presentation.dashboard.componants.CountCard
import com.example.tasks.Presentation.dashboard.componants.TaskComponant
import com.example.tasks.Presentation.destinations.TaskDetailsScreenRouteDestination
import com.example.tasks.Presentation.task_details.TaskId
import com.example.tasks.R
import com.example.tasks.core.models.Task
import com.example.tasks.core.presentation.AppButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun DashBoardScreenRoute(navigator: DestinationsNavigator) {
    val viewmodel: DashboardViewModel = hiltViewModel()
    val state by viewmodel.state.collectAsState()
    DashBoardScreen(onClicked = { task ->
        Log.d("Task id is", task.taskId.toString())
        val nav = TaskId(id = 23)
        navigator.navigate(TaskDetailsScreenRouteDestination(navArgs = nav))

    }, taskState = state, onEvent = viewmodel::onEvent)
}

@Composable
private fun DashBoardScreen(
    onClicked: (task: Task) -> Unit,
    taskState: TaskState,
    onEvent: (TaskEvent) -> Unit
) {
    var isAddTaskDialogOpen by rememberSaveable { mutableStateOf(false) }


    val listState = rememberLazyListState()
    val isNotScrolling by remember {
        derivedStateOf {
            !listState.isScrollInProgress
        }
    }


    AddOrUpdateTaskDialog(
        isOpen = isAddTaskDialogOpen,
        taskName = taskState.title,
        taskDescription = taskState.description,
        selectedColors = taskState.color.map { Color(it) },
        onTaskNameChange = { onEvent(TaskEvent.OnTitleChange(it)) },
        onTaskDescriptionChange = { onEvent(TaskEvent.OnDescriptionChange(it)) },
        onColorChange = { onEvent(TaskEvent.OnColorChange(it.map { it.toArgb() })) },
        onDismissRequest = { isAddTaskDialogOpen = false },
        onConfirmButtonClick = {
            onEvent(TaskEvent.SaveTask)
            isAddTaskDialogOpen = false
        }
    )

    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = { isAddTaskDialogOpen = true }) {
                if (isNotScrolling) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "")
                }

                Text("Add task")


            }
        }

    ) { paddingValues: PaddingValues ->
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                CountsCardSection(
                    completedTasks = "12",
                    totalTasks = taskState.tasks.size.toString(),
                    activeTasks = "12",
                    modifier = Modifier
                )

            }
            item {
                TaskComponant(
                   // showAddButton = true,
                    title = "Tasks",
                    drawable = R.drawable.img_tasks,
                    tasks = taskState.tasks,
                    modifier = Modifier,
                    onClicked = onClicked
                )
            }

            item {
                AppButton(
                    onClicked = {
                        isAddTaskDialogOpen = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    title = "Add Task"
                )
            }
            item {
                TaskComponant(
                    title = "Upcoming Tasks",
                    drawable = R.drawable.img_books,
                    tasks = taskState.tasks,
                    modifier = Modifier,
                    onClicked = onClicked

                )
            }
            item {
                TaskComponant(
                    title = "Recent Tasks",
                    drawable = R.drawable.img_lamp,
                    tasks = taskState.tasks,
                    modifier = Modifier,
                    onClicked = onClicked
                )
            }
            item {
                TaskComponant(
                    title = "Closed Tasks",
                    drawable = R.drawable.img_tasks,
                    tasks = taskState.tasks,
                    modifier = Modifier,
                    onClicked = onClicked
                )
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {

    CenterAlignedTopAppBar(
        title = {
            Text("Tasks", style = MaterialTheme.typography.displayMedium)
        },
    )
}

@Composable
fun CountsCardSection(
    modifier: Modifier,
    totalTasks: String,
    completedTasks: String,
    activeTasks: String,

    ) {

    Row {
        Spacer(modifier = Modifier.width(12.dp))
        CountCard(title = "Total Tasks", count = totalTasks, modifier = modifier.weight(1f))
        Spacer(modifier = Modifier.width(12.dp))
        CountCard(title = "Completed Tasks", count = completedTasks, modifier = modifier.weight(1f))
        Spacer(modifier = Modifier.width(12.dp))
        CountCard(title = "Active Tasks", count = activeTasks, modifier = modifier.weight(1f))
        Spacer(modifier = Modifier.width(12.dp))
    }
}