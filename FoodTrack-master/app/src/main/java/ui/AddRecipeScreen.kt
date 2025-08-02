package ui

package com.example.foodtrack.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddRecipeScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var steps by remember { mutableStateOf("") }

    Column(modifier = modifier.padding(16.dp)) {
        Text("Add New Recipe", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Recipe Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = steps,
            onValueChange = { steps = it },
            label = { Text("Steps") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = { /* Save logic */ }) {
            Text("Save Recipe")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddRecipeScreen() {
    AddRecipeScreen()
}
