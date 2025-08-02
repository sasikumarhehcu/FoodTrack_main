package com.example.mealmate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mealmate.ui.LoginScreen
import com.example.mealmate.ui.WelcomeScreen
import com.example.mealmate.ui.theme.MealmateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealmateTheme {
                var isLoggedIn by remember { mutableStateOf(false) }

                if (!isLoggedIn) {
                    WelcomeScreen(onContinueClick = { isLoggedIn = true })

                } else {
                    MainAppContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Mealmate Home") })
        },
        content = { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding).padding(24.dp)) {
                Text("Welcome! You're logged in.", style = MaterialTheme.typography.bodyLarge)
            }
        }
    )
}
