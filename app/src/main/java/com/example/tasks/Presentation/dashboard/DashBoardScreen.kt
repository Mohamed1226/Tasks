package com.example.tasks.Presentation.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun DashBoardScreen() {
    Scaffold(
        topBar = { TopBar() },

        ) { paddingValues: PaddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) { }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {

    CenterAlignedTopAppBar(
        title = {
            Text("Tasks", style = MaterialTheme.typography.displayMedium)
        },

        )
}