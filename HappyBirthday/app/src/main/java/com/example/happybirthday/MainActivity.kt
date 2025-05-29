package com.example.happybirthday


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HappyBirthdayTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingText("sasi")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingText(message: kotlin.text.String, modifier: Modifier = Modifier) {
    Text(
        text = message,
        modifier = modifier // It's good practice to apply the modifier
    )
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    HappyBirthdayTheme {
        GreetingText("sasi")
    }
}