package com.example.foodtrack.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test
import com.example.foodtrack.ui.AddRecipeScreen

class AddRecipeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun saveButton_isDisabled_whenFormIsEmpty() {
        composeTestRule.setContent {
            AddRecipeScreen(onAddRecipe = { true })
        }

        composeTestRule
            .onNodeWithText("Save Recipe")
            .assertIsNotEnabled()
    }

    @Test
    fun saveButton_isEnabled_whenFormIsFilled() {
        composeTestRule.setContent {
            AddRecipeScreen(onAddRecipe = { true })
        }

        composeTestRule.onNodeWithText("Recipe Name").performTextInput("Pasta")
        composeTestRule.onNodeWithText("Image URI").performTextInput("file://pasta.jpg")
        composeTestRule.onNodeWithText("Ingredients (comma-separated)").performTextInput("Tomato, Pasta")
        composeTestRule.onNodeWithText("Instructions").performTextInput("Boil pasta and mix with sauce")

        composeTestRule
            .onNodeWithText("Save Recipe")
            .assertIsEnabled()
    }
}
