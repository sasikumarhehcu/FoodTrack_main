package com.example.cheftrack

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class RecipeAdapter(
    private var recipeList: List<Recipe>,
    private val onFavoriteClick: (Recipe) -> Unit,
    private val onEditClick: (Recipe) -> Unit,
    private val onDeleteClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val tagsTextView: TextView = itemView.findViewById(R.id.tagsTextView)
        val recipeImageView: ImageView = itemView.findViewById(R.id.recipeImageView)
        val editIcon: ImageView = itemView.findViewById(R.id.editIcon)
        val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)
        val shareIcon: ImageView = itemView.findViewById(R.id.shareIcon)
        val favoriteIcon: ImageView = itemView.findViewById(R.id.favoriteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.titleTextView.text = recipe.title
        holder.tagsTextView.text = recipe.dietaryTags

        // Set image if available
        recipe.imageUri?.let {
            holder.recipeImageView.setImageURI(Uri.parse(it))
        } ?: holder.recipeImageView.setImageResource(R.drawable.placeholder_image)

        // Set favorite icon
        holder.favoriteIcon.setImageResource(
            if (recipe.isFavorite) R.drawable.ic_heart_filled
            else R.drawable.ic_heart_outline
        )

        // Toggle favorite
        holder.favoriteIcon.setOnClickListener {
            onFavoriteClick(recipe)
        }

        // Edit button
        holder.editIcon.setOnClickListener {
            onEditClick(recipe)
        }

        // Delete button
        holder.deleteIcon.setOnClickListener {
            onDeleteClick(recipe)
        }

        // Share
        holder.shareIcon.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, recipe.title)
                putExtra(Intent.EXTRA_TEXT, """
                    Check out this recipe!

                    Title: ${recipe.title}
                    Ingredients: ${recipe.ingredients}
                    Instructions: ${recipe.instructions}
                """.trimIndent())
            }
            context.startActivity(Intent.createChooser(intent, "Share via"))
        }

        // 🚀 Navigate to detail view on card tap
        holder.itemView.setOnClickListener {
            val bundle = android.os.Bundle().apply {
                putString("title", recipe.title)
                putString("ingredients", recipe.ingredients)
                putString("instructions", recipe.instructions)
                putString("imageUri", recipe.imageUri)
            }
            holder.itemView.findNavController().navigate(R.id.nav_recipe_detail, bundle)
        }
    }

    override fun getItemCount(): Int = recipeList.size

    fun updateList(newList: List<Recipe>) {
        recipeList = newList
        notifyDataSetChanged()
    }
}
