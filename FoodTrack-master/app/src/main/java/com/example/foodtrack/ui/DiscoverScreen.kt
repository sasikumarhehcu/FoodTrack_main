package com.example.foodtrack.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.foodtrack.R
import com.example.foodtrack.data.Recipe

@Composable
fun DiscoverScreen(onRecipeClick: (Recipe) -> Unit) {
    val sampleRecipes = listOf(
        Recipe(
            name = "Butter Chicken",
            imageUri = "butter_chicken",
            ingredients = listOf("Chicken", "Butter", "Cream", "Spices"),
            instructions = "1. Marinate chicken...\n2. Cook with butter and spices..."
        ),
        Recipe(
            name = "Vegetable Biryani",
            imageUri = "vegetable_biryani",
            ingredients = listOf("Rice", "Vegetables", "Spices"),
            instructions = "1. Cook rice...\n2. Layer with veggies..."
        ),
        Recipe(
            name = "Paneer Tikka",
            imageUri = "paneer_tikka",
            ingredients = listOf("Paneer", "Yogurt", "Spices"),
            instructions = "1. Marinate paneer...\n2. Grill until golden..."
        ),
                Recipe(
                name = "Mutton curry",
              imageUri = "butter_chicken",
                    ingredients = listOf("mutton", "Yogurt", "Spices"),
                    instructions = "1. Marinate mutton...\n2. Grill until golden..."
    )
    )

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Discover Recipes", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        LazyColumn {
            items(sampleRecipes) { recipe ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { onRecipeClick(recipe) }
                ) {
                    Row(modifier = Modifier.padding(12.dp)) {
                        val imageRes = when (recipe.imageUri) {
                            "butter_chicken" -> R.drawable.butter_chicken
                            "vegetable_biryani" -> R.drawable.veg_biryani
                            "paneer_tikka" -> R.drawable.paneer_butter_masala
                            "muttoncurry" -> R.drawable.butter_chicken
                            else -> android.R.drawable.ic_menu_gallery
                        }

                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = recipe.name,
                            modifier = Modifier
                                .size(100.dp) // Increased from 80.dp
                                .padding(end = 8.dp)
                                .border(1.dp, Color.Gray) // Added border
                                .shadow(4.dp), // Added shadow
                            contentScale = ContentScale.Crop
                        )

                        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                            Text(recipe.name, style = MaterialTheme.typography.titleMedium)
                            // Removed calories text
                        }
                    }
                }
            }
        }
    }
}