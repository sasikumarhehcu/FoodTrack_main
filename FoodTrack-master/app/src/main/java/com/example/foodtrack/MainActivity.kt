package com.example.foodtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp // Ensure dp is imported for padding
import com.example.foodtrack.ui.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Default theme implementation if foodtrackTheme is not defined
            // Define foodtrackTheme in com.example.foodtrack.ui.theme.foodtrackTheme.kt for custom styling
            val themeContent: @Composable () -> Unit = {
                MainContent()
            }
            try {
                //foodtrackTheme(themeContent)
            } catch (e: Exception) {
                MaterialTheme {
                    themeContent()
                }
            }
        }
    }

    @Composable
    fun MainContent() {
        var showWelcome by remember { mutableStateOf(true) }
        var isLoggedIn by remember { mutableStateOf(false) }
        var userName by remember { mutableStateOf("") }
        var userEmail by remember { mutableStateOf("") }

        when {
            showWelcome -> WelcomeScreen(
                onGetStartedClick = { showWelcome = false }
            )
            !isLoggedIn -> LoginScreen(
                onLoginSuccess = { name: String, email: String ->
                    userName = name
                    userEmail = email
                    isLoggedIn = true
                }
            )
            else -> MainAppContent(
                userName = userName,
                userEmail = userEmail,
                onLogout = { isLoggedIn = false }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent(
    userName: String,
    userEmail: String,
    onLogout: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val meals = remember { mutableStateListOf<String>() }
    val tabs = listOf("Home", "Add Recipe", "Favorites", "Profile")

    Scaffold(
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, label ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        label = { Text(label) },
                        icon = {
                            when (label) {
                                "Home" -> Icon(Icons.Default.Home, contentDescription = "Home")
                                "Add Recipe" -> Icon(Icons.Default.Add, contentDescription = "Add Recipe")
                                "Favorites" -> Icon(Icons.Default.Favorite, contentDescription = "Favorites")
                                "Profile" -> Icon(Icons.Default.Person, contentDescription = "Profile")
                                else -> Icon(Icons.Default.Info, contentDescription = "Info")
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                0 -> HomeScreen(
                    meals = meals
                )
                1 -> AddRecipeScreen(
                    onAddRecipe = { recipe: String ->
                        if (recipe.isNotBlank()) {
                            meals.add(recipe)
                            true
                        } else {
                            false
                        }
                    }
                )
                2 -> FavoritesScreen() // Placeholder; define in com.example.foodtrack.ui.FavoritesScreen.kt
                3 -> ProfileScreen(
                    userName = userName,
                    userEmail = userEmail,
                    onLogout = onLogout
                ) // Placeholder; define in com.example.foodtrack.ui.ProfileScreen.kt
            }
        }
    }
}