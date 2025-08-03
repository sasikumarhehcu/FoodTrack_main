package com.example.foodtrack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    userName: String,
    userEmail: String,
    onLogout: () -> Unit
) {
    var darkThemeEnabled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color(0xFFFFC107), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = userName.firstOrNull()?.uppercase() ?: "?",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(text = userName, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = userEmail, fontSize = 14.sp, color = Color.Gray)
            }

            TextButton(onClick = { /* Edit profile */ }) {
                Text("Edit profile")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Account", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Try all Plus features for free during your 7-day trial period!", fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { /* Try */ }) {
                    Text("Try for free")
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("System", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(12.dp))

        SettingsItem(icon = Icons.Default.Add, title = "Dark Mode") {
            Switch(
                checked = darkThemeEnabled,
                onCheckedChange = { darkThemeEnabled = it }
            )
        }
        SettingsItem(icon = Icons.Default.Done, title = "Languages")
        SettingsItem(icon = Icons.Default.Settings, title = "Preferences")

        Spacer(modifier = Modifier.height(24.dp))
        Text("Support", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(12.dp))
        SettingsItem(icon = Icons.Default.Email, title = "Send Feedback")
        SettingsItem(icon = Icons.Default.Warning, title = "Report a bug")

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Logout", color = Color.White)
        }
    }
}

@Composable
fun SettingsItem(
    icon: ImageVector,
    title: String,
    trailing: @Composable (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(title, fontSize = 16.sp, modifier = Modifier.weight(1f))
        trailing?.invoke()
    }
}
