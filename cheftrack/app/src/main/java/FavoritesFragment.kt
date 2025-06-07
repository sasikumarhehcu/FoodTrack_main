package com.example.cheftrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var favoriteRecyclerView: RecyclerView
    private lateinit var recipeDatabase: RecipeDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)

        favoriteRecyclerView = view.findViewById(R.id.favoriteRecyclerView)
        favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recipeDatabase = RecipeDatabase.getDatabase(requireContext())

        recipeAdapter = RecipeAdapter(
            recipeList = emptyList(),

            onFavoriteClick = { recipe ->
                val updated = recipe.copy(isFavorite = !recipe.isFavorite)
                lifecycleScope.launch {
                    recipeDatabase.recipeDao().insert(updated)
                }
            },

            onEditClick = { /* Optional: navigate to AddRecipeFragment if needed */ },

            onDeleteClick = { recipe ->
                lifecycleScope.launch {
                    recipeDatabase.recipeDao().delete(recipe)
                }
            }
        )

        favoriteRecyclerView.adapter = recipeAdapter

        recipeDatabase.recipeDao().getFavoriteRecipes().observe(viewLifecycleOwner) { favorites ->
            recipeAdapter.updateList(favorites)
        }

        return view
    }
}
