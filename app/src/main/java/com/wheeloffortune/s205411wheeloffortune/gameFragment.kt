package com.wheeloffortune.s205411wheeloffortune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wheeloffortune.s205411wheeloffortune.RecyclerViewItems.CategoryAdapter
import com.wheeloffortune.s205411wheeloffortune.RecyclerViewItems.LetterAdapter
import com.wheeloffortune.s205411wheeloffortune.RecyclerViewItems.StringItem
import kotlin.random.Random


class gameFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // The following code is taken from the following sources and modified:
        // https://stackoverflow.com/questions/56749461/how-to-set-an-onclicklistener-to-a-button-in-kotlin/56749553
        // https://www.youtube.com/watch?v=DI0NIk-7cz8&t=619s&ab_channel=Stevdza-San
        val view : View =  inflater.inflate(R.layout.fragment_game, container, false)
        val giveUpButton = view.findViewById<Button>(R.id.giveUpButton)
        val category = view.findViewById<TextView>(R.id.categoryTextView)
        giveUpButton.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.navigateToMainMenu)}

        // Setting string value in the textview to show chosen category
        if (currentCategory != null){
            if (currentCategory == "Random"){
                val categories = context?.resources?.getStringArray(R.array.Categories)
                currentCategory = getRandomString(1,categories as Array<String>)
            }
            category.text = "category: " + currentCategory
        }
        // Setting up RecyclerView
        // The folloing code is taken from the provided code in the slides for week 5 page 31, and modified
        val Letter = context?.resources?.getStringArray(R.array.Letters)


        var letterItemArray = ArrayList<StringItem>()



        //The following for loop is made by using the code on the web-side
        // https://kotlinlang.org/docs/control-flow.html#for-loops
        if (Letter != null) {
            for (i in Letter.indices){
                letterItemArray.add(i, StringItem(Letter[i]))

            }
        }


        val recyclerView: RecyclerView = view.findViewById(R.id.letters)
        // To change the recyclerviews appearance, I've used code from
        // https://stackoverflow.com/questions/39443986/make-a-multiline-recyclerview
        // and modified it
        recyclerView.layoutManager = GridLayoutManager(context, 7)
        recyclerView.adapter = LetterAdapter(letterItemArray)


        return view
    }
    // This method is made with code taken from
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.random/-random/
    private fun getRandomString(start : Int, StringArray: Array<String>): String{
        val randomNumber = Random.nextInt(start,StringArray.size)
        return StringArray[randomNumber]
    }

}