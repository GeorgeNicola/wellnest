package com.cst.wellnest.ui.calorieCounter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
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

class CalorieCounterFragment(): Fragment() {
    private val foodItemsViewModel: FoodItemViewModel by activityViewModels()
    private val foodItemAdapter: FoodItemAdapter = FoodItemAdapter(onDeleteClick = { position ->
        foodItemsViewModel.removeItem(position)
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_calorie_counter, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initClickActions(view)
        initFoodAdapter(view)
        initTotalMacronutrientsSection(view)

        initData()
    }

    private fun initData() {
        foodItemsViewModel.getItems()
    }

    private fun initClickActions(view: View) {
        val btnAddFood = view.findViewById<Button>(R.id.btn_add_food_item)
        val dayText = view.findViewById<TextView>(R.id.tv_day_selector)
        val btnGoToNextDay = view.findViewById<ImageButton>(R.id.btn_day_selector_next_day)
        val btnGoToPrevDay = view.findViewById<ImageButton>(R.id.btn_day_selector_prev_day)

        dayText.setText(foodItemsViewModel.getFormattedDate().toString())

        btnGoToNextDay.setOnClickListener {
            foodItemsViewModel.goToNextDay()
            dayText.setText(foodItemsViewModel.getFormattedDate().toString())
        }

        btnGoToPrevDay.setOnClickListener {
            foodItemsViewModel.goToPreviousDay()
            dayText.setText(foodItemsViewModel.getFormattedDate().toString())
        }

        foodItemsViewModel.selectedDate.observe(viewLifecycleOwner) {
            foodItemsViewModel.getItems()
        }

        btnAddFood.setOnClickListener {
            navigateToCalorieCounterSearch()
        }

    }

    private fun navigateToCalorieCounterSearch() {
        findNavController().navigate(
            R.id.action_calorieCounter_to_search
        )
    }

    private fun initFoodAdapter(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_items)
        recyclerView.adapter = foodItemAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        foodItemsViewModel.foodItems.observe(viewLifecycleOwner) { updatedList ->
            foodItemAdapter.setFoods(updatedList)
        }
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


        val totalMacros = foodItemsViewModel.getTotalCalories()

        totalCaloriesTV.text = view.context.getString(R.string.total_calories_format, totalMacros.calories, caloriesGoal)
        totalCaloriesProgress.progress = totalMacros.calories
        totalCaloriesProgress.max = caloriesGoal

        totalProteinTV.text = view.context.getString(R.string.total_protein_format, totalMacros.protein, proteinGoal)
        totalProteinProgress.progress = totalMacros.protein
        totalProteinProgress.max = proteinGoal

        totalCarbsTV.text = view.context.getString(R.string.total_carbs_format, totalMacros.carbs, carbsGoal)
        totalCarbsProgress.progress = totalMacros.carbs
        totalCarbsProgress.max = carbsGoal

        totalFatsTV.text = view.context.getString(R.string.total_fats_format, totalMacros.fats, fatsGoal)
        totalFatsProgress.progress = totalMacros.fats
        totalFatsProgress.max = fatsGoal

    }
}