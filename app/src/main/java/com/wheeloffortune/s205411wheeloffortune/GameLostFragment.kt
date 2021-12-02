package com.wheeloffortune.s205411wheeloffortune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation


class GameLostFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_game_lost, container, false)
        //Sets Main Menu button to navigate to StartScreen fragment
        view.findViewById<Button>(R.id.MainMenuButton).setOnClickListener{Navigation.findNavController(view).navigate(R.id.navigateToMainMenu)}
        // Sets play again button to navigate to categoriesFragment
        view.findViewById<Button>(R.id.PlayAgainButton).setOnClickListener{Navigation.findNavController(view).navigate(R.id.navigateToCategories)}

        // Sets screen text
        view.findViewById<TextView>(R.id.WordToBeGuessed).text = "The hidden word were: " + wordToBeGuessed

        return view
    }


}