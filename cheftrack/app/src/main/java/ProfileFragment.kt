package com.example.cheftrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var greetingTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        greetingTextView = view.findViewById(R.id.greetingTextView)

        val userEmail = FirebaseAuth.getInstance().currentUser?.email ?: "Guest"
        val greeting = getString(R.string.greeting_message, userEmail)
        greetingTextView.text = greeting

        return view
    }
}
