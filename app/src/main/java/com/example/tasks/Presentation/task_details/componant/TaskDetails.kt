package com.example.tasks.Presentation.task_details.componant

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tasks.Presentation.dashboard.factory.TaskColorFactory
import com.example.tasks.core.models.Task

@Composable
fun TaskDetails(task: Task, modifier: Modifier, onStatusClicked: (Task) -> Unit) {
    Column(modifier = modifier.padding(horizontal = 24.dp)) {
        Text(task.description, maxLines = 8, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = modifier.height(12.dp))
        Text(task.dueDate.toString(), maxLines = 8, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = modifier.height(12.dp))
        Box(
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium
                )
                .width(120.dp)
                .padding(4.dp)
                .clickable { onStatusClicked(task) }

        ) {
            Text(
                task.status.name,
                modifier = modifier.fillMaxSize(),
                color = TaskColorFactory.getStatusColor(task.status),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }

}