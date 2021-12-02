package com.wheeloffortune.s205411wheeloffortune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


class StartScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // The following code is taken from the following sources and modified:
        // https://stackoverflow.com/questions/56749461/how-to-set-an-onclicklistener-to-a-button-in-kotlin/56749553
        // https://www.youtube.com/watch?v=DI0NIk-7cz8&t=619s&ab_channel=Stevdza-San
        val view = inflater.inflate(R.layout.fragment_start_screen, container, false)
        val buttonStartGame : Button = view.findViewById(R.id.buttonStartGame)
        buttonStartGame.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.navigateToCategories)}
        val buttonHowToPlay : Button = view.findViewById(R.id.buttonHighscores)
        buttonHowToPlay.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.howToPlayFragment)}

        return view
    }


}