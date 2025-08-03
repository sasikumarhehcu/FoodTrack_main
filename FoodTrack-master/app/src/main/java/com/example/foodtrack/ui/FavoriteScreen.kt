package com.example.foodtrack.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment
import androidx.compose.foundation.clickable
import com.example.foodtrack.R
import com.example.foodtrack.ui.theme.FoodTrackTheme

@Composable
fun FavoritesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .background(Color(0xFFF0F4C3)) // Light green background
    ) {
        Text(
            "Your Favorite Recipes",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF4CAF50) // Green for freshness
        )
        Spacer(Modifier.height(12.dp))

        // List of 5 dishes with corresponding image resource IDs
        val dishes = listOf(
            Pair("Paneer Butter Masala", R.drawable.paneer_butter_masala),
            Pair("Chicken Curry", R.drawable.chicken_curry),
            Pair("Dal Makhani", R.drawable.dalmakhani),
            Pair("Butter Chicken", R.drawable.butter_chicken),
            Pair("Vegetable Biryani", R.drawable.veg_biryani)
        )

        dishes.forEach { (dishName, dishImage) -> // Destructure the Pair
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .background(Color(0xFFE7D296), shape = RoundedCornerShape(8.dp)), // Light gray card
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable { /* Add navigation if needed */ }
                ) {
                    // Load actual image from drawable
                    Image(
                        painter = painterResource(id = dishImage),
                        contentDescription = "Recipe Image for $dishName",
                        modifier = Modifier
                            .size(80.dp) // Adjust size as needed
                            .padding(end = 8.dp),
                        contentScale = ContentScale.Fit // Show the full image without cropping
                    )
                    Text(
                        "- $dishName",
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(4.dp),
                        color = Color(0xFF212121) // Dark text for readability
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFavoritesScreen() {
    FoodTrackTheme {
        FavoritesScreen()
    }
}
