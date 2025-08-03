package com.example.foodtrack.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodtrack.data.AppDatabase
import com.example.foodtrack.data.Recipe
import com.example.foodtrack.data.RecipeDao
import kotlinx.coroutines.launch
import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue


class AddRecipeViewModel(private val context: Context) : ViewModel() {
    // State for form fields
    var name by mutableStateOf("")
    var imageUri by mutableStateOf("")
    var ingredients by mutableStateOf("") // comma-separated
    var instructions by mutableStateOf("")

    private val db = AppDatabase.getDatabase(context)
    private val recipeDao: RecipeDao = db.recipeDao()

    // Function to handle recipe submission

    fun onSaveRecipe(onAddRecipe: (Recipe) -> Boolean) {
        val recipe = Recipe(
            name = name,
            imageUri = imageUri,
            ingredients = ingredients.split(",").map { it.trim() },
            instructions = instructions
        )

        Log.d("AddRecipeViewModel", "Attempting to save: $recipe")

        viewModelScope.launch {
            recipeDao.insert(recipe)
            Log.d("AddRecipeViewModel", "Recipe inserted into DB")

            if (onAddRecipe(recipe)) {
                name = ""
                imageUri = ""
                ingredients = ""
                instructions = ""
                Log.d("AddRecipeViewModel", "Recipe saved and form cleared")
            }
        }
    }


    // Check if the form is valid
    fun isFormValid(): Boolean {
        return name.isNotBlank() && imageUri.isNotBlank() &&
                ingredients.isNotBlank() && instructions.isNotBlank()
    }
}