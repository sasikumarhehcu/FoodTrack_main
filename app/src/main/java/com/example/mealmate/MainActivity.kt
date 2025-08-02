package com.example.mealmate

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
import androidx.compose.ui.unit.dp
import com.example.mealmate.ui.*
import com.example.mealmate.ui.theme.MealmateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealmateTheme {
                var showWelcome by remember { mutableStateOf(true) }
                var isLoggedIn by remember { mutableStateOf(false) }

                when {
                    showWelcome -> WelcomeScreen { showWelcome = false }
                    !isLoggedIn -> LoginScreen { isLoggedIn = true }
                    else -> MainAppContent(onLogout = { isLoggedIn = false })
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent(onLogout: () -> Unit) {
    var selectedTab by remember { mutableStateOf(0) }
    val meals = remember { mutableStateListOf<String>() }

    val tabs = listOf("Home", "Add", "Favorites", "Profile")

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
                                "Home" -> Icon(Icons.Default.Home, contentDescription = null)
                                "Add" -> Icon(Icons.Default.Add, contentDescription = null)
                                "Favorites" -> Icon(Icons.Default.Favorite, contentDescription = null)
                                "Profile" -> Icon(Icons.Default.Person, contentDescription = null)
                                else -> Icon(Icons.Default.Info, contentDescription = null)
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (selectedTab) {
                0 -> HomeScreen(meals)
                1 -> AddMealScreen(onAddMeal = { meal -> meals.add(meal) })
                2 -> FavoritesScreen()
                3 -> ProfileScreen(onLogout = onLogout)
            }
        }
    }
}
