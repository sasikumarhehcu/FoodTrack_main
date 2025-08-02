package com.example.foodtrack.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

data class Recipe(
    val name: String,
    val imageUri: String, // Mock URI for now
    val ingredients: List<String>,
    val instructions: String
)

@Composable
fun AddRecipeScreen(modifier: Modifier = Modifier, onAddRecipe: (Recipe) -> Boolean) {
    var name by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf("") } // Mock image URI
    var ingredients by remember { mutableStateOf("") } // Comma-separated
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
            label = { Text("Image URI (e.g., path or URL)") },
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
                if (name.isNotBlank() && imageUri.isNotBlank() && ingredients.isNotBlank() && instructions.isNotBlank()) {
                    val recipeIngredients = ingredients.split(",").map { it.trim() }
                    val recipe = Recipe(name, imageUri, recipeIngredients, instructions)
                    if (onAddRecipe(recipe)) {
                        name = ""
                        imageUri = ""
                        ingredients = ""
                        instructions = ""
                    }
                }
            },
            enabled = name.isNotBlank() && imageUri.isNotBlank() && ingredients.isNotBlank() && instructions.isNotBlank()
        ) {
            Text("Save Recipe")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAddRecipeScreen() {
    val previewRecipes = remember { mutableStateListOf<Recipe>() }
    AddRecipeScreen { recipe ->
        if (recipe.name.isNotBlank() && recipe.imageUri.isNotBlank() && recipe.ingredients.isNotEmpty() && recipe.instructions.isNotBlank()) {
            previewRecipes.add(recipe)
            true
        } else {
            false
        }
    }
}