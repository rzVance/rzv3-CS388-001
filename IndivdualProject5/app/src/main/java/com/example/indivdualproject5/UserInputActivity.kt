package com.example.indivdualproject5

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class UserInputActivity : AppCompatActivity(){
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.user_input)

        val db = DBHelper(this, null)

        val saveBTN: Button = findViewById(R.id.save_water)
        val getFood: EditText = findViewById(R.id.water_edit_text)
        val getCal: EditText = findViewById(R.id.notes_edit_text)

        saveBTN.setOnClickListener {
            val foodName = getFood.text.toString()

            val calCount = getCal.text.toString()

            db.addWater(foodName, calCount)

            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }
}