package com.example.cheftrack

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AddRecipeFragment : Fragment() {

    private lateinit var titleEditText: EditText
    private lateinit var ingredientsEditText: EditText
    private lateinit var instructionsEditText: EditText
    private lateinit var tagsEditText: EditText
    private lateinit var selectedImageView: ImageView
    private lateinit var saveButton: Button
    private lateinit var selectImageButton: Button

    private var selectedImageUri: Uri? = null
    private var recipeId: Int = 0

    // Image picker launcher
    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            selectedImageUri = result.data?.data
            selectedImageUri?.let {
                selectedImageView.setImageURI(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_add_recipe, container, false)

        // Initialize views
        titleEditText = view.findViewById(R.id.editTextTitle)
        ingredientsEditText = view.findViewById(R.id.editTextIngredients)
        instructionsEditText = view.findViewById(R.id.editTextInstructions)
        tagsEditText = view.findViewById(R.id.editTextTags)
        selectedImageView = view.findViewById(R.id.selectedImageView)
        saveButton = view.findViewById(R.id.buttonSave)
        selectImageButton = view.findViewById(R.id.buttonSelectImage)

        // Handle image select button
        selectImageButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            imagePickerLauncher.launch(intent)
        }

        // Check for arguments (edit mode)
        arguments?.let { args ->
            recipeId = args.getInt("id", 0)
            if (recipeId != 0) {
                titleEditText.setText(args.getString("title", ""))
                ingredientsEditText.setText(args.getString("ingredients", ""))
                instructionsEditText.setText(args.getString("instructions", ""))
                tagsEditText.setText(args.getString("tags", ""))
                val imageUri = args.getString("imageUri")
                if (!imageUri.isNullOrEmpty()) {
                    selectedImageUri = Uri.parse(imageUri)
                    selectedImageView.setImageURI(selectedImageUri)
                }
                saveButton.text = "Update Recipe"
            }
        }

        // Handle save/update button
        saveButton.setOnClickListener {
            saveRecipe()
        }

        return view
    }

    private fun saveRecipe() {
        val title = titleEditText.text.toString().trim()
        val ingredients = ingredientsEditText.text.toString().trim()
        val instructions = instructionsEditText.text.toString().trim()
        val tags = tagsEditText.text.toString().trim()

        if (title.isEmpty() || ingredients.isEmpty() || instructions.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val recipe = Recipe(
            id = recipeId, // 0 for new, >0 for update
            title = title,
            ingredients = ingredients,
            instructions = instructions,
            dietaryTags = tags,
            imageUri = selectedImageUri?.toString()
        )

        val db = RecipeDatabase.getDatabase(requireContext())

        lifecycleScope.launch {
            db.recipeDao().insert(recipe)
            Toast.makeText(requireContext(), "Recipe saved!", Toast.LENGTH_SHORT).show()

            // Clear form (optional)
            titleEditText.text.clear()
            ingredientsEditText.text.clear()
            instructionsEditText.text.clear()
            tagsEditText.text.clear()
            selectedImageView.setImageResource(0)
            selectedImageUri = null
            recipeId = 0
        }
    }
}
