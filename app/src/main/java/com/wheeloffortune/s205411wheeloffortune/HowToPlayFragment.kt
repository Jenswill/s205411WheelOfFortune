package com.wheeloffortune.s205411wheeloffortune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation


class HowToPlayFragment : Fragment() {




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_how_to_play, container, false)
        //Defines main menu button
        val mainMenuButton = view.findViewById<Button>(R.id.MainMenuButton)
        //Sets click listener to navigate back to main menu
        mainMenuButton.setOnClickListener{Navigation.findNavController(view).navigate(R.id.navigateToMainMenu)}



        return view
    }


}