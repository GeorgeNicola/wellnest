package com.cst.wellnest.ui.calorieCounter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.wellnest.R
import com.cst.wellnest.adapters.FoodItemAdapter
import com.cst.wellnest.models.FoodItem
import com.cst.wellnest.models.FoodItemViewModel

class CalorieCounterSearchFragment(): Fragment() {
    private val foodItemsViewModel: FoodItemViewModel by activityViewModels()
    private val foodItemAdapter: FoodItemAdapter = FoodItemAdapter(onDeleteClick = { position ->
        foodItemsViewModel.removeItem(position)
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_calorie_counter_search, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickActions(view)
        initFoodAdapter(view)
    }

    private fun initClickActions(view: View) {
        val btnSearch = view.findViewById<Button>(R.id.btn_search)
        btnSearch.setOnClickListener {
            // Navigate to calorie counter fragment
            findNavController().navigate(
                R.id.action_calorieCounterSearch_to_calorieCounter
            )
        }
    }

    private fun initFoodAdapter(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_search_results)
        recyclerView.adapter = foodItemAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        foodItemsViewModel.foodItems.observe(viewLifecycleOwner) { results ->
            foodItemAdapter.setFoods(results)
        }
    }
}