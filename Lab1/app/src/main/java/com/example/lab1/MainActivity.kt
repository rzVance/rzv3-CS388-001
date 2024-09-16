package com.example.lab1

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var counter = 0
    var incrementValue = 1 // Keep track of increment value dynamically
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            val textView = findViewById<TextView>(R.id.counter)

            val imageButton = findViewById<ImageButton>(R.id.imageButton)

            val logoButton = findViewById<Button>(R.id.logoBtn)
            val upgradeButton = findViewById<Button>(R.id.upgradeBtn)

            // imageButton
            imageButton.setOnClickListener {
                counter += incrementValue
                textView.text = counter.toString()

                if (counter === 100) {
                    upgradeButton.visibility = View.VISIBLE
                }
                if (counter === 50) {
                    logoButton.visibility = View.VISIBLE
                }
            }

            // upgradeButton
            upgradeButton.setOnClickListener {
                incrementValue = 2
                upgradeButton.visibility = View.INVISIBLE
                Toast.makeText(this, "x2 ! !", Toast.LENGTH_SHORT).show()
            }

            // logoButton
            logoButton.setOnClickListener {
                imageButton.setImageResource(R.drawable.card)
                logoButton.text = "new button"
                logoButton.visibility = View.INVISIBLE
                Toast.makeText(this, "Diamonds ! !", Toast.LENGTH_SHORT).show()
            }

            insets
        }
    }
}
