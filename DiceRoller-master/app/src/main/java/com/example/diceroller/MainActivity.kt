package com.example.diceroller

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                DiceScreen()
            }
        }
    }
}

@Composable
fun DiceScreen() {
    val context = LocalContext.current
    var diceRoll by remember { mutableStateOf(1) }

    val diceImage = when (diceRoll) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Image(
            painter = painterResource(id = diceImage),
            contentDescription = "Dice showing $diceRoll",
            modifier = Modifier
                .size(180.dp)
                .align(Alignment.Center)
        )

        Button(
            onClick = {
                diceRoll = (1..6).random()
                Toast.makeText(context, "Rolled: $diceRoll", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("Roll Dice")
        }
    }
}
