package com.example.IndividualProject6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class FoodListFragment : Fragment() {
    private val food = mutableListOf<FoodEntity>()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.food_fragment_list, container, false)
        val layoutManager = LinearLayoutManager(context)
        foodRecyclerView = view.findViewById(R.id.food_recycler_view)
        foodRecyclerView.setHasFixedSize(true)
        foodAdapter = FoodAdapter(view.context, food)

        lifecycleScope.launch {
            (activity?.application as BitFitApp).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(entity.foodAmount, entity.foodNotes)
                }.also { mappedList ->
                    food.clear()
                    food.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()

                }
            }
        }

        foodRecyclerView.adapter = foodAdapter

        layoutManager.also {
            val dividerItemDecoration = DividerItemDecoration(context, it.orientation)
            foodRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(): FoodListFragment{
            return FoodListFragment()
        }
    }
}