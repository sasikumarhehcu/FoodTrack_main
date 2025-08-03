package com.example.foodtrack.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodtrack.R // Ensure this matches your app's R file
import androidx.compose.ui.graphics.Color

@Composable
fun WelcomeScreen(onGetStartedClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF4CAF50), Color(0xFFFFC107)) // Green to Yellow gradient
                )
            )
    ) {
        // Background image (ensure foodtrack_welcome is in res/drawable)
        Image(
            painter = painterResource(id = R.drawable.foodtrack_welcome),
            contentDescription = "Welcome Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 32.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo (ensure foodtrack_logo is in res/drawable)
            Image(
                painter = painterResource(id = R.drawable.foodtrack_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome to FoodTrack!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF44336), // Red for a spicy welcome
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onGetStartedClick,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350)), // Coral red button
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Get started", color = Color.White)
            }
        }
    }
}