package com.example.foodtrack.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodtrack.data.Recipe



@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    meals: SnapshotStateList<Recipe>,
    onRecipeClick: (Recipe) -> Unit
) {
    var foodItem by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var totalCalories by remember { mutableIntStateOf(0) }

    // Define a simple food list with calories per 100g
    val foodCalories = mapOf(
        "Paneer Butter Masala" to 265,  // Calories per 100g
        "Chicken Curry" to 200,
        "Dal Makhani" to 120,
        "Butter Chicken" to 350,
        "Vegetable Biryani" to 140
    )

    Column(modifier = modifier.padding(16.dp)) {
        // Recipe Section
        Text("Recent Recipes", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        LazyColumn {
            items(meals) { recipe ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { onRecipeClick(recipe) }
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
                            contentDescription = "Recipe Image",
                            modifier = Modifier
                                .size(64.dp)
                                .padding(end = 8.dp),
                            contentScale = ContentScale.Crop
                        )
                        Text(recipe.name, modifier = Modifier.align(Alignment.CenterVertically))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp)) // Adding space between the sections

        // Calorie Calculator Section
        Text(
            "Food Calorie Calculator",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(Modifier.height(16.dp))

        // Food Item Input
        OutlinedTextField(
            value = foodItem,
            onValueChange = { foodItem = it },
            label = { Text("Enter Food Item") },
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(8.dp))

        // Quantity Input
        OutlinedTextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Enter Quantity (in grams)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(16.dp))

        // Calculate Button
        Button(
            onClick = {
                val foodCaloriesPerUnit = foodCalories[foodItem]
                if (foodCaloriesPerUnit != null && quantity.isNotEmpty()) {
                    val qty = quantity.toIntOrNull()
                    if (qty != null) {
                        totalCalories = (foodCaloriesPerUnit * qty) / 100
                    } else {
                        totalCalories = 0
                    }
                } else {
                    totalCalories = 0
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate Calories")
        }

        Spacer(Modifier.height(16.dp))

        // Display total calories
        if (totalCalories > 0) {
            Text("Total Calories: $totalCalories kcal", style = MaterialTheme.typography.bodyLarge)
        } else if (foodItem.isNotEmpty() && quantity.isNotEmpty()) {
            Text("Food item not found or invalid quantity.", color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val previewMeals = remember {
        mutableStateListOf(
            Recipe(
                id = 0,
                name = "Spicy Tofu Bowl",
                imageUri = "uri://placeholder",
                ingredients = listOf("Tofu", "Spice"),
                instructions = "Cook tofu with spice."
            )
        )
    }
    HomeScreen(meals = previewMeals, onRecipeClick = {})
}