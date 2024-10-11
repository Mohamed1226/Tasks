package com.example.tasks.Presentation.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tasks.Presentation.dashboard.componants.CountCard
import com.example.tasks.Presentation.dashboard.componants.TaskComponant
import com.example.tasks.core.models.Task


@Composable
fun DashBoardScreen() {

    val tasks = listOf(
        Task(title = "task 1", color = Task.colors[0]),
        Task(title = "task 1", color = Task.colors[0]),
        Task(title = "task 1", color = Task.colors[0]),
        Task(title = "task 1", color = Task.colors[0]),
    )
    Scaffold(
        topBar = { TopBar() },


        ) { paddingValues: PaddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item {
                CountsCardSection(
                    completedTasks = "12",
                    totalTasks = "24",
                    activeTasks = "12",
                    modifier = Modifier
                )
                TaskComponant(tasks = tasks, modifier = Modifier)
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