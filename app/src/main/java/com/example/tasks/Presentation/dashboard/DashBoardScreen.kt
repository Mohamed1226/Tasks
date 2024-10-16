package com.example.tasks.Presentation.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.Presentation.dashboard.componants.AddOrUpdateTaskDialog
import com.example.tasks.Presentation.dashboard.componants.CountCard
import com.example.tasks.Presentation.dashboard.componants.TaskComponant
import com.example.tasks.R
import com.example.tasks.core.models.Task
import com.example.tasks.core.presentation.AppButton


@Composable
fun DashBoardScreen() {
    var isAddTaskDialogOpen by rememberSaveable { mutableStateOf(false) }

    var name by rememberSaveable { mutableStateOf("") }
    var selectedColor by rememberSaveable { mutableStateOf(Task.colors[0]) }

    var desc by rememberSaveable { mutableStateOf("") }

    val tasks = listOf(
        Task(title = "task 1", color = Task.colors[0]),
        Task(title = "task 1", color = Task.colors[0]),
        Task(title = "task 1", color = Task.colors[0]),
        Task(title = "task 1", color = Task.colors[0]),
    )



    AddOrUpdateTaskDialog(
        isOpen = isAddTaskDialogOpen,
        taskName = name,
        taskDescription = desc,
        selectedColors = selectedColor,
        onTaskNameChange = { name = it },
        onTaskDescriptionChange = { desc = it },
        onColorChange = {  selectedColor = it},
        onDismissRequest = { isAddTaskDialogOpen = false },
        onConfirmButtonClick = {
            /// add or update function
            isAddTaskDialogOpen = false
        }
    )

    Scaffold(
        topBar = { TopBar() },


        ) { paddingValues: PaddingValues ->
        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(paddingValues)
        ) {
            item {
                CountsCardSection(
                    completedTasks = "12",
                    totalTasks = "24",
                    activeTasks = "12",
                    modifier = Modifier
                )

            }
            item {
                TaskComponant(
                    showAddButton = true,
                    title = "Tasks",
                    drawable = R.drawable.img_tasks,
                    tasks = tasks,
                    modifier = Modifier
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
                    title = "Start Task"
                )
            }
            item {
                TaskComponant(
                    title = "Upcoming Tasks",
                    drawable = R.drawable.img_books,
                    tasks = tasks,
                    modifier = Modifier
                )
            }
            item {
                TaskComponant(
                    title = "Recent Tasks",
                    drawable = R.drawable.img_lamp,
                    tasks = tasks,
                    modifier = Modifier
                )
            }
            item {
                TaskComponant(
                    title = "Closed Tasks",
                    drawable = R.drawable.img_tasks,
                    tasks = tasks,
                    modifier = Modifier
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