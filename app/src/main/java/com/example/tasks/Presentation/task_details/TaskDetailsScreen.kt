package com.example.tasks.Presentation.task_details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tasks.Presentation.task_details.componant.AppTopAppBar
import com.example.tasks.Presentation.task_details.componant.TaskDetails
import com.example.tasks.core.models.Task


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailsScreen() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            AppTopAppBar(
                title = "state.subjectName",
                onBackButtonClick = {},
                onDeleteButtonClick = { },
                onEditButtonClick = {},
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


