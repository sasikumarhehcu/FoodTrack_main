package com.example.mealmate.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(meals: List<String>) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)) {

        Text("Home", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        if (meals.isEmpty()) {
            Text("Your personalized meal list will appear here.")
        } else {
            LazyColumn {
                items(meals) { meal ->
                    Text(
                        text = meal,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    Divider()
                }
            }
        }
    }
}
