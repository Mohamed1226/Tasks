package com.example.tasks.Presentation.dashboard.componants

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CountCard(modifier: Modifier, title: String, count: String) {

ElevatedCard(modifier = modifier) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp, vertical = 12.dp
            ).height(100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(title, style = MaterialTheme.typography.labelLarge, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(20.dp))
        Text(count, style = MaterialTheme.typography.bodySmall.copy(fontSize = 30.sp))
    }
}


}