package com.wheeloffortune.s205411wheeloffortune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        //initializes giveUpButton and specifies where it should navigate to
        val giveUpButton = view.findViewById<Button>(R.id.giveUpButton)
        giveUpButton.setOnClickListener{ Navigation.findNavController(view).navigate(R.id.navigateToGameLost)}
        //sets gamemode to spinning phase (0 = spinning phase, 1 = guessing phase)
        gameMode = 0
        // initializes gamePhase textview
        view.findViewById<TextView>(R.id.gamePhase).text = "Spin the wheel!"

        // initializes category textView
        initCategory(view)

        //Chooses a random word within category
        wordToBeGuessed = getWordToBeGuessed()

        //Hides word from player
        hideWord(view)



        //setting up spin button
        view.findViewById<Button>(R.id.spinButton).setOnClickListener{
            if (gameMode == 0){
                //Getting a random string from saved StringArray 'Wheel'
                view.findViewById<TextView>(R.id.wheelView).text =
                    getRandomString(0,context?.resources?.getStringArray(R.array.Wheel) as Array<String>)
                //Executes game logic for the wheel
                wheelLogic()
            }
        }



        // defines recyclerview
        val recyclerView: RecyclerView = view.findViewById(R.id.letters)
        // initializes recyclerview to show letters which can be guessed
        initLetters(recyclerView)



        return view
    }

    // This method is made with code taken from
    // https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.random/-random/
    private fun getRandomString(start : Int, StringArray: Array<String>): String{
        //generates a random number within boundaries
        val randomNumber = Random.nextInt(start,StringArray.size)
        //Returns random string from string array
        return StringArray[randomNumber]
    }

    fun guessLetter(letter : String){
        // defines textview
        val textView = view?.findViewById<TextView>(R.id.wordString)


        val answerArray = wordToBeGuessed?.split("")

        if(wordToBeGuessed?.contains(letter) == true){
            //getting string with all currently guessed letters
            val hiddenString =  textView?.text as String
            //Removes spaces from the String
            // Method .replace found at https://stackoverflow.com/questions/60028103/how-to-remove-all-the-whitespaces-from-a-string-in-kotlin
            val hiddenStringArray = hiddenString.replace(" ","").split("")
            var pts : Int = 0

            var newString  =  ""

            //Checks if the letter appears in the word
            if (answerArray != null) {
                for (i in answerArray.indices){
                        if (answerArray[i] != ""){
                            if (answerArray[i].equals(letter)){
                                newString += (letter + " ")
                                //Calculating how many points the player gets
                                pts += view?.findViewById<TextView>(R.id.wheelView)?.text.toString().toInt()
                            }else{
                                newString += (hiddenStringArray[i] + " ")
                            }
                        }


                }
                //Revealing the letter
                textView.text = newString
                // updating how many points the player have
                val ptsView : TextView? = view?.findViewById(R.id.points)
                ptsView?.text = (ptsView?.text.toString().toInt() + pts).toString()

                //Checking if the player won the game
                if (!newString.contains("_")){
                    //Sets players final number of points
                    points = ptsView?.text.toString()
                    //Navigates to GameLostFragment
                    view?.let { Navigation.findNavController(it).navigate(R.id.gameWonFragment) }
                }

            }


        }else{
            //Reduces number of lives by 1
            val lives : TextView? = view?.findViewById(R.id.Lives)
            lives?.text = (lives?.text.toString().toInt() - 1).toString()
            //checks if game is lost
            if (lives?.text.toString() == "0"){
                //Navigates to GameLostFragment
                view?.let { Navigation.findNavController(it).navigate(R.id.navigateToGameLost) }
            }
        }

        view?.findViewById<TextView>(R.id.gamePhase)?.text = "Spin the wheel!"
        gameMode = 0
    }

    private fun initCategory(view: View){
        //This is a function to setup the textview 'categoryTextView' to show the current category
        val category = view.findViewById<TextView>(R.id.categoryTextView)
        // Setting string value in the textview to show chosen category
        if (currentCategory != null){
            //Choses random category if 'Random' is chosen
            if (currentCategory == "Random"){
                val categories = context?.resources?.getStringArray(R.array.Categories)
                currentCategory = getRandomString(1,categories as Array<String>)
            }
            // Writes category in category textView
            category?.text = "category: " + currentCategory

        }
    }

    private fun initLetters(recyclerView: RecyclerView){
        // Setting up RecyclerView
        // The following code is taken from the provided code in the slides for week 5 page 31, and modified
        val Letter = context?.resources?.getStringArray(R.array.Letters)

        //Now a list of type StringItem is made
        var letterItemArray = ArrayList<StringItem>()



        //The following for loop is made by using the code on the web-side
        // https://kotlinlang.org/docs/control-flow.html#for-loops
        if (Letter != null) {
            for (i in Letter.indices){
                //Here the items for the recyclerview is made and added to the list
                letterItemArray.add(i, StringItem(Letter[i]))

            }
        }


        // To change the recyclerviews appearance, I've used code from
        // https://stackoverflow.com/questions/39443986/make-a-multiline-recyclerview
        // and modified it
        recyclerView.layoutManager = GridLayoutManager(context, 7)
        recyclerView.adapter = LetterAdapter(this,letterItemArray)
    }

    private  fun getAllWords(category: String): Array<String> {
        val allWords: Array<String>
        //made by code taken and from
        //https://stackoverflow.com/questions/42211527/getpackagename-in-fragment
        //https://stackoverflow.com/questions/42524916/is-it-possible-to-handle-r-array-string-array-name-through-a-string-variable-in
        //and modified
        val id : Int? = context?.resources?.getIdentifier(category,"array",context?.packageName)
        allWords = context?.resources?.getStringArray(id!!) as Array<String>
        return allWords

    }

    private fun getWordToBeGuessed(): String{

        val allWords = getAllWords(currentCategory!!)

        return getRandomString(0,allWords)
    }

    private fun hideWord(view :View){
        var hiddenString: String = ""

        for (i in wordToBeGuessed?.split("")!!.indices){
            // Creates a string containing an "_ " for each letter in the hidden word
            if (wordToBeGuessed?.split("")!![i] != ""){
                hiddenString += "_ "
            }

        }

        view.findViewById<TextView>(R.id.wordString)?.text = hiddenString
    }

    private fun wheelLogic(){
        val text : String = view?.findViewById<TextView>(R.id.wheelView)?.text.toString()

        if(text == "extra turn"){
            //Increases the number of lives by 1
            val lives : TextView? = view?.findViewById(R.id.Lives)
            lives?.text = (lives?.text.toString().toInt() + 1).toString()
        }else if(text == "miss turn"){
            //Reduces the number of lives by 1
            val lives : TextView? = view?.findViewById(R.id.Lives)
            lives?.text = (lives?.text.toString().toInt() - 1).toString()
            //checks if game is lost
            if (lives?.text.toString() == "0"){
                //Navigates to GameLostFragment
                view?.let { Navigation.findNavController(it).navigate(R.id.navigateToGameLost) }
            }
        }else if (text == "bankrupt"){
            // sets players points to 0
            view?.findViewById<TextView>(R.id.points)?.text = "0"

        }else{
            view?.findViewById<TextView>(R.id.gamePhase)?.text = "Guess a letter!"
            gameMode = 1
        }

    }



}