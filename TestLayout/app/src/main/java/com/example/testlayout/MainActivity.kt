package com.example.testlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testlayout.ui.theme.TestLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestLayoutTheme {
                // layout component for material design
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Basic_layout(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )

//                    Subject(subjectCode = "3406",  modifier = Modifier.padding(innerPadding))
                    SubjectColumn(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Basic_layout(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestLayoutTheme {
        Basic_layout("Android")
    }
}

@Composable
fun Subject(subjectCode: String, modifier: Modifier = Modifier) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.Green),
        text = "Code of subject is: $subjectCode"
    )
}
@Preview(showBackground = true)
@Composable
fun SubjectPreview() {
    Subject("CP3406")
}

@Composable
fun SubjectColumn(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Subject(subjectCode = "CP3406")
        Subject(subjectCode = "CP5307")
        Subject(subjectCode = "CP3307")
    }
}




@Preview(showBackground = true)
@Composable
fun SubjectColumnPreview() {
    SubjectColumn()
}