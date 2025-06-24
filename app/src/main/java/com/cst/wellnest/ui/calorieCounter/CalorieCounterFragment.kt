package com.cst.wellnest.ui.calorieCounter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cst.wellnest.R
import com.cst.wellnest.adapters.FoodItemAdapter
import com.cst.wellnest.models.FoodItem

class CalorieCounterFragment(
    val foodItemAdapter: FoodItemAdapter = FoodItemAdapter()
): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_calorie_counter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadFoodAdapter(view)
        populateTotalCalories(view)
    }

    private fun loadFoodAdapter(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_items)

        val items = mutableListOf(
            FoodItem("Chicken Breast", 110, 20, 0, 2, 100),
            FoodItem("Chicken Breast", 110, 20, 0, 2, 200),
            FoodItem("Chicken Breast", 110, 20, 0, 2, 100),
            FoodItem("Chicken Breast", 110, 20, 0, 2, 100),
            FoodItem("Chicken Breast", 110, 20, 0, 2, 100),
            FoodItem("Chicken Breast", 110, 20, 0, 2, 100),
            FoodItem("Chicken Breast", 110, 20, 0, 2, 100),
            FoodItem("Chicken Breast", 110, 20, 0, 2, 100),
            FoodItem("Chicken Breast", 110, 20, 0, 2, 100),
            FoodItem("Chicken Breast", 120, 20, 0, 2, 100),

            )

        foodItemAdapter.addFoods(items)

        val layoutManager = LinearLayoutManager(requireContext())

        recyclerView.adapter = foodItemAdapter
        recyclerView.layoutManager = layoutManager
    }

    private fun populateTotalCalories(view: View) {
        val totalCalories = view.findViewById<TextView>(R.id.tv_total_calories)
        val totalProtein = view.findViewById<TextView>(R.id.tv_total_protein)
        val totalCarbs = view.findViewById<TextView>(R.id.tv_total_carbs)
        val totalFats = view.findViewById<TextView>(R.id.tv_total_fats)

        val foodAdapter = foodItemAdapter.getTotalCalories()

        totalCalories.setText(view.context.getString(R.string.calories_format, foodAdapter.calories))
        totalProtein.text = view.context.getString(R.string.protein_format, foodAdapter.protein)
        totalCarbs.text = view.context.getString(R.string.carbs_format, foodAdapter.carbs)
        totalFats.text = view.context.getString(R.string.fats_format, foodAdapter.fats)
    }
}