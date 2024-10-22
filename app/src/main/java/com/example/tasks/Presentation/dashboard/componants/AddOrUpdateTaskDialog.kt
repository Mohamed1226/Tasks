package com.example.tasks.Presentation.dashboard.componants

import androidx.compose.runtime.Composable


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tasks.core.common.AppDateFormatter
import com.example.tasks.core.models.Task
import com.example.tasks.core.presentation.AppDatePickerDialog
import com.example.tasks.core.presentation.AppTextField
import java.time.Instant


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddOrUpdateTaskDialog(
    isOpen: Boolean,
    title: String = "Add/Update task",
    selectedColors: List<Color>,
    taskName: String,
    taskDescription: String,
    onColorChange: (List<Color>) -> Unit,
    onTaskNameChange: (String) -> Unit,
    onTaskDescriptionChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit
) {
    var taskNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var taskDescriptionError by rememberSaveable { mutableStateOf<String?>(null) }
    var dateDialogIsOpen by rememberSaveable { mutableStateOf(false) }
    var datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli(),


    )

    taskNameError = when {
        taskName.isBlank() -> "Please enter task name."
        taskName.length < 2 -> "task name is too short."
        taskName.length > 20 -> "task name is too long."
        else -> null
    }
    taskDescriptionError = when {
        taskDescription.isBlank() -> "Please enter task description"
        taskDescription.length < 2 -> "task name is too short."
        taskDescription.length > 20 -> "task name is too long."
        else -> null
    }

    if (dateDialogIsOpen) {
        AppDatePickerDialog(
            onDismissRequest = { dateDialogIsOpen = false},
            isOpen = dateDialogIsOpen,
            state = datePickerState,
            onConfirmButtonClicked =  { dateDialogIsOpen = false}
        )
    }

    if (isOpen) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = title) },
            text = {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Task.colors.forEach { colors ->
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(CircleShape)
                                    .border(
                                        width = 1.dp,
                                        color = if (colors == selectedColors) Color.Black
                                        else Color.Transparent,
                                        shape = CircleShape
                                    )
                                    .background(brush = Brush.verticalGradient(colors))
                                    .clickable { onColorChange(colors) }
                            )
                        }
                    }
                    AppTextField(
                        value = taskName,
                        onValueChange = onTaskNameChange,
                        label = "task Name",
                        isError = taskNameError != null && taskName.isNotBlank(),
                        supportingText = taskNameError.orEmpty(),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AppTextField(
                        value = taskDescription,
                        onValueChange = onTaskDescriptionChange,
                        label = "Task description",
                        isError = taskDescriptionError != null && taskDescription.isNotBlank(),
                        supportingText = taskDescriptionError.orEmpty(),
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    Column {
                        Text("Due date", style = MaterialTheme.typography.labelLarge)
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(AppDateFormatter().convertMillisToDate(datePickerState.selectedDateMillis)  ?: "", style = MaterialTheme.typography.bodyLarge)
                            IconButton(onClick = {
                                dateDialogIsOpen = true
                            }) {
                                Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                            }


                        }
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = "Cancel")
                }
            },
            confirmButton = {
                TextButton(
                    onClick = onConfirmButtonClick,
                    enabled = taskNameError == null && taskDescriptionError == null && selectedColors.isNotEmpty()
                ) {
                    Text(text = "Save")
                }
            }
        )
    }
}