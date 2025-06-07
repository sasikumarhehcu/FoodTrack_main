package com.example.cheftrack

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeDatabase: RecipeDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var allRecipes: List<Recipe> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        recyclerView = view.findViewById(R.id.recipeRecyclerView)
        searchView = view.findViewById(R.id.searchView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        recipeDatabase = RecipeDatabase.getDatabase(requireContext())

        recipeAdapter = RecipeAdapter(
            recipeList = emptyList(),
            onFavoriteClick = { updatedRecipe ->
                val toggled = updatedRecipe.copy(isFavorite = !updatedRecipe.isFavorite)
                lifecycleScope.launch {
                    recipeDatabase.recipeDao().insert(toggled)
                }
            },
            onEditClick = { /* your edit logic */ },
            onDeleteClick = { recipe ->
                lifecycleScope.launch {
                    recipeDatabase.recipeDao().delete(recipe)
                }
            }
        )

        recyclerView.adapter = recipeAdapter

        // ✅ Observe and filter recipes
        recipeDatabase.recipeDao().getAllRecipes().observe(viewLifecycleOwner) { recipes ->
            allRecipes = recipes

            val prefs = requireActivity().getSharedPreferences("diet_prefs", Context.MODE_PRIVATE)
            val vegan = prefs.getBoolean("vegan", false)
            val vegetarian = prefs.getBoolean("vegetarian", false)
            val glutenFree = prefs.getBoolean("gluten_free", false)

            val filtered = recipes.filter { recipe ->
                val tags = recipe.dietaryTags.lowercase()

                val matchesVegan = !vegan || tags.contains("vegan")
                val matchesVegetarian = !vegetarian || tags.contains("vegetarian")
                val matchesGlutenFree = !glutenFree || tags.contains("gluten")

                matchesVegan && matchesVegetarian && matchesGlutenFree
            }

            recipeAdapter.updateList(filtered)
        }

        // ✅ Search filtering
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                val filtered = allRecipes.filter {
                    it.title.contains(newText ?: "", ignoreCase = true) ||
                            it.dietaryTags.contains(newText ?: "", ignoreCase = true)
                }
                recipeAdapter.updateList(filtered)
                return true
            }
        })

        return view
    }
}
