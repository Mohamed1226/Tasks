package com.example.tasks.core.presentation

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun AppTextField(
    value: String,
    label: String,
    supportingText: String,
    isError: Boolean,
    onValueChange: (String) -> Unit,
    keyboardType :KeyboardType
) {

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        singleLine = true,
        isError = isError,
        supportingText = { Text(text = supportingText) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}
