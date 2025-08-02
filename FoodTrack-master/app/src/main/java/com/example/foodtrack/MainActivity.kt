package com.example.foodtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodtrack.ui.HomeScreen
import com.example.foodtrack.ui.AddRecipeScreen
import com.example.foodtrack.ui.FavoritesScreen
import com.example.foodtrack.ui.ProfileScreen
import com.example.foodtrack.ui.WelcomeScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodTrackTheme {
                var showWelcome by remember { mutableStateOf(true) }

                if (showWelcome) {
                    WelcomeScreen(onGetStartedClick = { showWelcome = false })
                } else {
                    FoodTrackApp()
                }
            }
        }
    }
}

@Composable
fun FoodTrackApp() {
    var selectedTab by remember { mutableStateOf(0) }

    val tabs = listOf("Home", "Add", "Favorites", "Profile")
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.Add,
        Icons.Default.Favorite,
        Icons.Default.Person
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(icons[index], contentDescription = title) },
                        label = { Text(title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> AddRecipeScreen()
                2 -> FavoritesScreen()
                3 -> ProfileScreen()
            }
        }
    }
}
