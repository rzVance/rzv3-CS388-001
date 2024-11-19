package com.example.IndividualProject6

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class FoodDetailActivity : AppCompatActivity() {
    private lateinit var foodAmountInput: EditText
    private lateinit var foodNotesInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_food_detail_activity)


        foodAmountInput= findViewById(R.id.enter_food_amount)
        foodNotesInput= findViewById(R.id.enter_food_notes)

        findViewById<Button>(R.id.add_food_button_detail).setOnClickListener{
            val newFoodEntity = FoodEntity(
                foodAmountInput.text.toString(),
                foodNotesInput.text.toString(),
            )

            lifecycleScope.launch(IO) {
                (application as BitFitApp).db.foodDao().insert(newFoodEntity)
            }


            foodAmountInput.getText().clear()
            foodNotesInput.getText().clear()
        }


    }
}