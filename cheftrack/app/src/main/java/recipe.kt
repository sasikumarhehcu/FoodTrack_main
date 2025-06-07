package com.example.cheftrack

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val ingredients: String,
    val instructions: String,
    val dietaryTags: String,
    val imageUri: String? = null,
    val isFavorite: Boolean = false
)
