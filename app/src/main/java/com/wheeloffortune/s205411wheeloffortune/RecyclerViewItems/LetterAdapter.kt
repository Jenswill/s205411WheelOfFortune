package com.wheeloffortune.s205411wheeloffortune.RecyclerViewItems



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

import androidx.recyclerview.widget.RecyclerView
import com.wheeloffortune.s205411wheeloffortune.R
import com.wheeloffortune.s205411wheeloffortune.gameFragment

/*All of the following code is taken from week 5 slide 30 and modified*/
class LetterAdapter(gamefragment: gameFragment, private val letters: ArrayList<StringItem>) : RecyclerView.Adapter<LetterAdapter.ViewHolder>()
{
    val fragment = gamefragment
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val view : View = view

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_simple_button,parent,false)
        return ViewHolder(viewLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val button : Button = holder.view.findViewById(R.id.Button)
        button.text = letters[position].stringItem
        button.textSize = 15F
        // The code for the clicklistener is taken from the following sources and modified:
        // https://stackoverflow.com/questions/56749461/how-to-set-an-onclicklistener-to-a-button-in-kotlin/56749553
        // https://www.youtube.com/watch?v=DI0NIk-7cz8&t=619s&ab_channel=Stevdza-Sa
        // https://stackoverflow.com/questions/61252600/delete-item-from-recyclerview-on-button-click-kotlin-mvvm-firestore
        button.setOnClickListener {

            fragment.guessLetter(letters[position].stringItem)
            letters.removeAt(position)

            notifyDataSetChanged()
        }

    }

    override fun getItemCount(): Int {
        return letters.size
    }




}