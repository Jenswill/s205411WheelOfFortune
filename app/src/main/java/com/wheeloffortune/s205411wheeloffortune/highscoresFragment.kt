package com.wheeloffortune.s205411wheeloffortune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView


class highscoresFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        // The following code is taken from the following sources and modified:
        // https://stackoverflow.com/questions/56749461/how-to-set-an-onclicklistener-to-a-button-in-kotlin/56749553
        // https://www.youtube.com/watch?v=DI0NIk-7cz8&t=619s&ab_channel=Stevdza-San
        val view = inflater.inflate(R.layout.fragment_highscores, container, false)

        view.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.navigateToStartScreen)}


        return view

    }

    // https://discuss.kotlinlang.org/t/vararg-vs-array-parameter/4068/9



    //made by code taken and from
    //https://stackoverflow.com/questions/42211527/getpackagename-in-fragment
    //https://stackoverflow.com/questions/42524916/is-it-possible-to-handle-r-array-string-array-name-through-a-string-variable-in
    //and modified
    fun funtest(test: String){

        val id : Int? = context?.resources?.getIdentifier(test,"array",context?.packageName)
        if (id != null) {
            context?.resources?.getStringArray(id)
        }
    }

}