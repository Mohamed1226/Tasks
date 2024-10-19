package com.example.tasks.Presentation.task_details.componant

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tasks.Presentation.dashboard.factory.TaskColorFactory
import com.example.tasks.core.enums.TaskStatus
import com.example.tasks.core.models.Task


@Composable
fun TaskStatusComponant(
    task: Task, modifier: Modifier,
    onStatusClicked: (TaskStatus) -> Unit
) {
    var selectedStatus by remember { mutableStateOf(task.status) }
    var isExpanded by remember { mutableStateOf(false) }
    val icon = if (isExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Box(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium
            )
            .padding(4.dp)

    ) {
        Row(
            Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                task.status.name,
                modifier = modifier.fillMaxSize(),
                color = TaskColorFactory.getStatusColor(task.status),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Icon(icon, "contentDescription")

        }
        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
//                    modifier = Modifier
//                        .width(with(LocalDensity.current){mTextFieldSize.width.toDp()})
        ) {
            com.example.tasks.core.enums.TaskStatus.entries.forEach { label ->
                DropdownMenuItem(
                    text = { Text(text = label.name) },

                    onClick = {
                        task.status = label
                        selectedStatus = label
                        onStatusClicked(selectedStatus)
                        isExpanded = false
                    })
            }
        }

    }
}