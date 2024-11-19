package com.example.IndividualProject6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "TotalsFragment"
class TotalsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_totals, container, false)
        lifecycleScope.launch(Dispatchers.IO) {
            var  totalFood = (activity?.application as BitFitApp).db.foodDao().totalFoodAmount()
            view.findViewById<TextView?>(R.id.total_food_amount_value).text = totalFood + " cal"
        }

        return view
    }
    companion object {

        fun newInstance(): TotalsFragment{
            return TotalsFragment()
        }
    }
}