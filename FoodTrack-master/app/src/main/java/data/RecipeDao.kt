package com.example.foodtrack.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RecipeDao {

    @Insert
    suspend fun insert(recipe: Recipe)

    @Query("SELECT * FROM recipe")
    suspend fun getAll(): List<Recipe>
}
