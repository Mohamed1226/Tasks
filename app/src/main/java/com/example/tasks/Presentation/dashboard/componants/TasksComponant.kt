package com.example.tasks.Presentation.dashboard.componants

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasks.R
import com.example.tasks.core.models.Task


@Composable
fun TaskComponant(showAddButton : Boolean = false, onClicked: (task: Task) -> Unit,title: String,drawable: Int,modifier: Modifier, tasks: List<Task>) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Row(
            modifier = modifier.fillMaxSize().padding(bottom = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                title,
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp)
            )
            if(showAddButton)
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }

        }
        if (tasks.isEmpty()) {
            Image(
                painter = painterResource(id = drawable), contentDescription = "",
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .size(120.dp)
            )
            Text(
                "You do not have any tasks !!",
                modifier = modifier.fillMaxSize(),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )
        } else {
            LazyRow (
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = modifier.padding(start = 12.dp, end = 12.dp)
            ){

                items(count = tasks.size) { index ->
                    TaskUI(drawable = drawable,modifier = modifier, task = tasks[index], colors = tasks[index].color,
                        onClicked = onClicked,
                        onStatusClicked = {}
                        )
                }
            }
        }


    }
}