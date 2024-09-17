package com.example.individualproject1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Corrected call to getRandomFourLetterWord
        val wordToGuess = FourLetterWordList.FourLetterWordList.getRandomFourLetterWord()
        findViewById<TextView>(R.id.word).text = wordToGuess
        val submit = findViewById<Button>(R.id.button)

        fun checkGuess(guess: String): String {
            var result = ""
            for (i in 0..3) {
                if (guess[i] == wordToGuess[i]) {
                    result += "O"
                } else if (guess[i] in wordToGuess) {
                    result += "+"
                } else {
                    result += "X"
                }
            }
            return result
        }

        var guesses = 1
        submit.setOnClickListener {
            val guessInput = findViewById<TextInputEditText>(R.id.guess)
            val guessText = guessInput.text.toString().uppercase()
            val guessResult = checkGuess(guessText)
            if (guessText.length == 4) {


                when (guesses) {
                    1 -> {
                        findViewById<TextView>(R.id.check1).text = guessResult
                        findViewById<TextView>(R.id.guess1).text = "Guess #1: $guessText"
                    }
                    2 -> {
                        findViewById<TextView>(R.id.check2).text = guessResult
                        findViewById<TextView>(R.id.guess2).text = "Guess #2: $guessText"
                    }
                    3 -> {
                        findViewById<TextView>(R.id.check3).text = guessResult
                        findViewById<TextView>(R.id.guess3).text = "Guess #3: $guessText"
                        findViewById<TextView>(R.id.word).visibility = View.VISIBLE
                        submit.visibility = View.INVISIBLE
                    }
                }


                if (guessResult == "OOOO") {
                    Toast.makeText(this, "Congratulations, you win!", Toast.LENGTH_SHORT).show()
                    submit.visibility = View.INVISIBLE
                    findViewById<TextView>(R.id.word).visibility = View.VISIBLE
                }

                guesses++



            }
            guessInput.setText("")

            if (guesses > 3 && guessResult != "OOOO") {
                Toast.makeText(this, "No more guesses", Toast.LENGTH_SHORT).show()
                submit.visibility = View.INVISIBLE
                findViewById<TextView>(R.id.word).visibility = View.VISIBLE
            }
        }
    }
}
