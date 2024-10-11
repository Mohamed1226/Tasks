package com.example.tasks.Presentation.dashboard.componants

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tasks.R
import com.example.tasks.core.models.Task

@Composable
fun TaskUI(
    modifier: Modifier, task: Task,
    colors: List<Color>,
    onClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .clickable { onClicked() }
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(colors),
                shape = MaterialTheme.shapes.medium
            )
            .padding(
                horizontal = 12.dp, vertical = 12.dp
            )
    ) {

        Column(

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.img_tasks), contentDescription = "",
                modifier = Modifier.height(100.dp)

            )
            Text(
                task.title,
                modifier = modifier.fillMaxSize(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        }
    }
}