package com.example.foodtrack.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodtrack.data.Recipe



@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    onClose: () -> Unit // ✅ clearer parameter name
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Recipe: ${recipe.name}", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))

        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Replace with real image later
            contentDescription = "Recipe Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(16.dp))
        Text("Ingredients:", style = MaterialTheme.typography.titleMedium)
        recipe.ingredients.forEach { ingredient ->
            Text("- $ingredient", modifier = Modifier.padding(start = 8.dp))
        }

        Spacer(Modifier.height(16.dp))
        Text("Instructions:", style = MaterialTheme.typography.titleMedium)
        Text(recipe.instructions, modifier = Modifier.padding(start = 8.dp))

        Spacer(Modifier.height(24.dp))

        Button(onClick = onClose) {
            Text("Close")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRecipeDetailScreen() {
    RecipeDetailScreen(
        recipe = Recipe(
            name = "Spicy Tofu Bowl",
            imageUri = "uri://placeholder",
            ingredients = listOf("Tofu", "Spice", "Oil"),
            instructions = "1. Heat oil.\n2. Cook tofu with spice."
        ),
        onClose = { /* preview: do nothing */ }
    )
}