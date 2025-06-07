package com.example.cheftrack

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import android.widget.Toast

class DietaryFilterFragment : Fragment() {

    private lateinit var veganCheckBox: CheckBox
    private lateinit var vegetarianCheckBox: CheckBox
    private lateinit var glutenFreeCheckBox: CheckBox
    private lateinit var saveButton: Button

    private val PREF_NAME = "diet_prefs"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_dietary_filter, container, false)

        veganCheckBox = view.findViewById(R.id.checkBoxVegan)
        vegetarianCheckBox = view.findViewById(R.id.checkBoxVegetarian)
        glutenFreeCheckBox = view.findViewById(R.id.checkBoxGlutenFree)
        saveButton = view.findViewById(R.id.savePreferencesButton)

        loadPreferences()

        saveButton.setOnClickListener {
            savePreferences()
            Toast.makeText(requireContext(), "Preferences saved", Toast.LENGTH_SHORT).show()
        }

        return view
    }

    private fun loadPreferences() {
        val prefs = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        veganCheckBox.isChecked = prefs.getBoolean("vegan", false)
        vegetarianCheckBox.isChecked = prefs.getBoolean("vegetarian", false)
        glutenFreeCheckBox.isChecked = prefs.getBoolean("gluten_free", false)
    }

    private fun savePreferences() {
        val prefs = requireActivity().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putBoolean("vegan", veganCheckBox.isChecked)
            putBoolean("vegetarian", vegetarianCheckBox.isChecked)
            putBoolean("gluten_free", glutenFreeCheckBox.isChecked)
            apply()
        }
    }
}
