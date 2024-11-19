package com.example.IndividualProject6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragmentManager: FragmentManager = supportFragmentManager


        val foodListFragment: Fragment = FoodListFragment()
        val analyticsFragment: Fragment = TotalsFragment()


        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)


        findViewById<Button>(R.id.add_food_button)?.setOnClickListener {
            val intent = Intent(this, FoodDetailActivity::class.java)
            startActivity(intent)
        }


        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_home -> fragment = foodListFragment
                R.id.action_totals -> fragment = analyticsFragment

            }
            fragmentManager.beginTransaction().replace(R.id.food_frame_layout, fragment).commit()
            true
        }


        bottomNavigationView.selectedItemId = R.id.action_home
    }
}