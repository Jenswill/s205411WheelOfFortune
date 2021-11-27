package com.wheeloffortune.s205411wheeloffortune.RecyclerViewItems


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

import androidx.recyclerview.widget.RecyclerView
import com.wheeloffortune.s205411wheeloffortune.R
import com.wheeloffortune.s205411wheeloffortune.currentCategory

/*All of the following code is taken from week 5 slide 30 and modified*/
class CategoryAdapter(private val categories: ArrayList<CategoryItem>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view : View = view

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category,parent,false)
        return ViewHolder(viewLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val button : Button = holder.view.findViewById(R.id.CategoryButton)
       button.text = categories[position].category
        // The code for the clicklistener is taken from the following sources and modified:
        // https://stackoverflow.com/questions/56749461/how-to-set-an-onclicklistener-to-a-button-in-kotlin/56749553
        // https://www.youtube.com/watch?v=DI0NIk-7cz8&t=619s&ab_channel=Stevdza-Sa
       button.setOnClickListener {
            currentCategory = categories[position].category
          Navigation.findNavController(holder.view).navigate(R.id.navigateToGame)
        }

    }

    override fun getItemCount(): Int {
        return categories.size
    }




}