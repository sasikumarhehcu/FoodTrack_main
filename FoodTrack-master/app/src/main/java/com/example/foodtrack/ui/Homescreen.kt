package com.example.foodtrack.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment




@Composable
fun HomeScreen(modifier: Modifier = Modifier, meals: SnapshotStateList<Recipe>, onRecipeClick: (Recipe) -> Unit) {
    Column(modifier = modifier.padding(16.dp)) {
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
                        // Mock image display (replace with Coil or Glide for real images)
                        Image(
                            painter = painterResource(id = android.R.drawable.ic_menu_gallery), // Placeholder
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
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val previewMeals = remember { SnapshotStateList<Recipe>().apply {
        add(Recipe("Spicy Tofu Bowl", "uri://placeholder", listOf("Tofu", "Spice"), "Cook tofu with spice."))
    } }
    HomeScreen(meals = previewMeals, onRecipeClick = {})
}