package com.example.tasks.Presentation.task_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.tasks.Presentation.dashboard.componants.AddOrUpdateTaskDialog
import com.example.tasks.Presentation.dashboard.componants.DeleteTaskDialog
import com.example.tasks.Presentation.task_details.componant.AppTopAppBar
import com.example.tasks.Presentation.task_details.componant.TaskDetails
import com.example.tasks.core.models.Task


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    var isEditTaskDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }

    var name by rememberSaveable { mutableStateOf("") }
    var selectedColor by rememberSaveable { mutableStateOf(Task.colors[0]) }
    var desc by rememberSaveable { mutableStateOf("") }

    DeleteTaskDialog(
        isOpen = isDeleteDialogOpen,
        onDismissRequest = { isDeleteDialogOpen = false },
        onConfirmButtonClick = {
            /// delete function
            isDeleteDialogOpen = false
        }
    )
    AddOrUpdateTaskDialog(
        isOpen = isEditTaskDialogOpen,
        taskName = name,
        taskDescription = desc,
        selectedColors = selectedColor,
        onTaskNameChange = { name = it },
        onTaskDescriptionChange = { desc = it },
        onColorChange = { selectedColor = it },
        onDismissRequest = { isEditTaskDialogOpen = false },
        onConfirmButtonClick = {
            /// add or update function
            isEditTaskDialogOpen = false
        }
    )


    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AppTopAppBar(
                title = "state.subjectName",
                onBackButtonClick = {},
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
            item {
                TaskDetails(
                    task = Task(
                        title = "task 1",
                        description = "Hello dsfdsklmfcxmcvxmcv mfdvkfdkj; ;klfdgvkfkdf l;kfdgjfdkljfd",
                        color = Task.colors[0]
                    ),
                    modifier = Modifier,
                    onStatusClicked = { task -> })
            }
        }
    }


}


