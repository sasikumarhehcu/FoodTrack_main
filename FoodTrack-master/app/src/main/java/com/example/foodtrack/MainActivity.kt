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
import androidx.compose.ui.platform.LocalContext
import com.example.foodtrack.data.AppDatabase
import com.example.foodtrack.data.Recipe
import com.example.foodtrack.ui.*
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }

    @Composable
    fun MainContent() {
        var showWelcome by remember { mutableStateOf(true) }
        var isLoggedIn by remember { mutableStateOf(false) }
        var userName by remember { mutableStateOf("") }
        var userEmail by remember { mutableStateOf("") }
        var selectedRecipe by remember { mutableStateOf<Recipe?>(null) }
        var showSignup by remember { mutableStateOf(false) } // NEW

        when {
            selectedRecipe != null -> RecipeDetailScreen(
                recipe = selectedRecipe!!,
                onClose = { selectedRecipe = null }
            )

            showWelcome -> WelcomeScreen(onGetStartedClick = { showWelcome = false })

            showSignup -> SignupScreen(
                onSignupComplete = { showSignup = false }
            )

            !isLoggedIn -> LoginScreen(
                onLoginSuccess = { name, email ->
                    userName = name
                    userEmail = email
                    isLoggedIn = true
                },
                onSignupClick = { showSignup = true } // NEW
            )

            else -> MainAppContent(
                userName = userName,
                userEmail = userEmail,
                onLogout = { isLoggedIn = false },
                onRecipeClick = { recipe -> selectedRecipe = recipe }
            )
        }
    }

    @Composable
    fun MainAppContent(
        userName: String,
        userEmail: String,
        onLogout: () -> Unit,
        onRecipeClick: (Recipe) -> Unit
    ) {
        var selectedTab by remember { mutableIntStateOf(0) }
        val meals = remember { mutableStateListOf<Recipe>() }
        val tabs = listOf("Home", "Add Recipe", "Favorites", "Discover", "Profile")

        val context = LocalContext.current
        val db = remember { AppDatabase.getDatabase(context) }
        val coroutineScope = rememberCoroutineScope()

        // Load existing recipes from DB once
        LaunchedEffect(Unit) {
            val saved = db.recipeDao().getAll()
            meals.clear()
            meals.addAll(saved)
        }

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
                                    "Discover" -> Icon(Icons.Default.Search, contentDescription = "Discover")
                                    "Profile" -> Icon(Icons.Default.Person, contentDescription = "Profile")
                                    else -> Icon(Icons.Default.Info, contentDescription = "Other")
                                }
                            }
                        )
                    }
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when (selectedTab) {
                    0 -> HomeScreen(meals = meals, onRecipeClick = onRecipeClick)
                    1 -> AddRecipeScreen(onAddRecipe = { recipe ->
                        if (recipe.name.isNotBlank() && recipe.imageUri.isNotBlank() &&
                            recipe.ingredients.isNotEmpty() && recipe.instructions.isNotBlank()
                        ) {
                            coroutineScope.launch { db.recipeDao().insert(recipe) }
                            meals.add(recipe)
                            true
                        } else false
                    })
                    2 -> FavoritesScreen()
                    3 -> DiscoverScreen(onRecipeClick = onRecipeClick)
                    4 -> ProfileScreen(userName = userName, userEmail = userEmail, onLogout = onLogout)
                }
            }
        }
    }
}
