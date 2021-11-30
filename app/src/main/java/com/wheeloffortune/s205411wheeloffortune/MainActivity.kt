package com.wheeloffortune.s205411wheeloffortune

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
var currentCategory : String ? = null
var wordToBeGuessed : String ? = null
var gameMode: Int ? = null
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}