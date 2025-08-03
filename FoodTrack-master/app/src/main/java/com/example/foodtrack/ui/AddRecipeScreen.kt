package com.example.foodtrack.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodtrack.data.Recipe

@Composable
fun AddRecipeScreen(
    modifier: Modifier = Modifier,
    onAddRecipe: (Recipe) -> Boolean
) {
    var name by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf("") }
    var ingredients by remember { mutableStateOf("") } // comma-separated
    var instructions by remember { mutableStateOf("") }

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
            value = imageUri,
            onValueChange = { imageUri = it },
            label = { Text("Image URI") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = ingredients,
            onValueChange = { ingredients = it },
            label = { Text("Ingredients (comma-separated)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = instructions,
            onValueChange = { instructions = it },
            label = { Text("Instructions") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 5
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                val recipe = Recipe(
                        name = name,
                imageUri = imageUri,
                ingredients = ingredients.split(",").map { it.trim() }, // convert from String to List<String>
                instructions = instructions
                )

                if (onAddRecipe(recipe)) {
                    name = ""
                    imageUri = ""
                    ingredients = ""
                    instructions = ""
                }
            },
            enabled = name.isNotBlank() && imageUri.isNotBlank() &&
                    ingredients.isNotBlank() && instructions.isNotBlank()
        ) {
            Text("Save Recipe")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddRecipeScreen() {
    AddRecipeScreen { true }
}
