package com.cst.wellnest.ui.calorieCounter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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

        initFoodAdapter(view)
        initTotalMacronutrientsSection(view)
    }

    private fun initFoodAdapter(view: View) {
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

    private fun initTotalMacronutrientsSection(view: View) {
        val caloriesGoal = 3000
        val proteinGoal = 250
        val carbsGoal = 250
        val fatsGoal = 70

        val totalCaloriesTV = view.findViewById<TextView>(R.id.tv_macronutrients_calories)
        val totalCaloriesProgress = view.findViewById<ProgressBar>(R.id.progress_macronutrients_calories)
        val totalProteinTV = view.findViewById<TextView>(R.id.tv_macronutrients_protein)
        val totalProteinProgress = view.findViewById<ProgressBar>(R.id.progress_macronutrients_protein)
        val totalCarbsTV = view.findViewById<TextView>(R.id.tv_macronutrients_carbs)
        val totalCarbsProgress = view.findViewById<ProgressBar>(R.id.progress_macronutrients_carbs)
        val totalFatsTV = view.findViewById<TextView>(R.id.tv_macronutrients_fats)
        val totalFatsProgress = view.findViewById<ProgressBar>(R.id.progress_macronutrients_fats)


        val foodAdapter = foodItemAdapter.getTotalCalories()

        totalCaloriesTV.text = view.context.getString(R.string.total_calories_format, foodAdapter.calories, caloriesGoal)
        totalCaloriesProgress.progress = foodAdapter.calories
        totalCaloriesProgress.max = caloriesGoal

        totalProteinTV.text = view.context.getString(R.string.total_protein_format, foodAdapter.protein, proteinGoal)
        totalProteinProgress.progress = foodAdapter.protein
        totalProteinProgress.max = proteinGoal

        totalCarbsTV.text = view.context.getString(R.string.total_carbs_format, foodAdapter.carbs, carbsGoal)
        totalCarbsProgress.progress = foodAdapter.carbs
        totalCarbsProgress.max = carbsGoal

        totalFatsTV.text = view.context.getString(R.string.total_fats_format, foodAdapter.fats, fatsGoal)
        totalFatsProgress.progress = foodAdapter.fats
        totalFatsProgress.max = fatsGoal

    }
}