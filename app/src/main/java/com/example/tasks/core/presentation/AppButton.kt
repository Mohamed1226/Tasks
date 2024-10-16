package com.example.tasks.core.presentation

import androidx.compose.foundation.border
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp


@Composable
fun AppButton(onClicked: () -> Unit, title: String, modifier: Modifier) {

    Button(
        onClick = onClicked,
        modifier = modifier
    ) {
        Text(title, style = MaterialTheme.typography.bodyLarge)
    }
}