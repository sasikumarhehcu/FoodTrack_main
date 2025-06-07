package com.example.cheftrack

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Query("SELECT * FROM Recipe")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE isFavorite = 1")
    fun getFavoriteRecipes(): LiveData<List<Recipe>>

    @Delete
    suspend fun delete(recipe: Recipe)
}
