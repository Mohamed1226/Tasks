package com.example.tasks.core.presentation

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDatePickerDialog(
    isOpen: Boolean,
    state: DatePickerState,
    onDismissRequest: () -> Unit,
    onConfirmButtonClicked: () -> Unit,
) {

    if (isOpen) {
        DatePickerDialog(
            onDismissRequest = onDismissRequest,

            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text("Cancel", style = MaterialTheme.typography.labelLarge)
                }
            },
            confirmButton = {
                TextButton(onClick = onConfirmButtonClicked) {
                    Text("Ok", style = MaterialTheme.typography.labelLarge)
                }
            }

        ) {
            DatePicker(state = state)
        }
    }

}

