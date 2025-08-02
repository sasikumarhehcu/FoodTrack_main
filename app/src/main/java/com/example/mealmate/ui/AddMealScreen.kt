package com.example.mealmate.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddMealScreen(onAddMeal: (String) -> Unit) {
    var mealName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(24.dp)) {
        Text("Add a New Meal", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = mealName,
            onValueChange = { mealName = it },
            label = { Text("Meal name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (mealName.isNotBlank()) {
                    onAddMeal(mealName.trim())
                    mealName = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Meal")
        }
    }
}
