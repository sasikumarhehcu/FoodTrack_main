package com.example.cheftrack

import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class RecipeDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

        val titleTextView: TextView = view.findViewById(R.id.detailTitle)
        val ingredientsTextView: TextView = view.findViewById(R.id.detailIngredients)
        val instructionsTextView: TextView = view.findViewById(R.id.detailInstructions)
        val imageView: ImageView = view.findViewById(R.id.detailImage)

        arguments?.let { bundle ->
            titleTextView.text = bundle.getString("title")
            ingredientsTextView.text = bundle.getString("ingredients")
            instructionsTextView.text = bundle.getString("instructions")

            val imageUri = bundle.getString("imageUri")
            if (!imageUri.isNullOrEmpty()) {
                imageView.setImageURI(Uri.parse(imageUri))
            } else {
                imageView.setImageResource(R.drawable.placeholder_image) // optional fallback
            }
        }

        return view
    }
}
