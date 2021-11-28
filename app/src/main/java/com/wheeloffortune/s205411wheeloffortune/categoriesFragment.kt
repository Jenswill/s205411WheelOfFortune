package com.wheeloffortune.s205411wheeloffortune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wheeloffortune.s205411wheeloffortune.RecyclerViewItems.CategoryAdapter
import com.wheeloffortune.s205411wheeloffortune.RecyclerViewItems.StringItem


/**
 * A simple [Fragment] subclass.
 * Use the [categoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class categoriesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // The following code is taken from the following sources and modified:
        // https://www.youtube.com/watch?v=DI0NIk-7cz8&t=619s&ab_channel=Stevdza-San
        val view = inflater.inflate(R.layout.fragment_categories, container, false)


        // The folloing code is taken from the provided code in the slides for week 5 page 31, and modified
        val categoryString = context?.resources?.getStringArray(R.array.Categories)

        var categoryItemArray = ArrayList<StringItem>()

        //The following for loop is made by using the code on the web-side
        // https://kotlinlang.org/docs/control-flow.html#for-loops
        if (categoryString != null) {
            for (i in categoryString.indices){
                categoryItemArray.add(i,StringItem(categoryString[i]))
            }
        }


        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.adapter = CategoryAdapter(categoryItemArray)
        return view
    }


}

