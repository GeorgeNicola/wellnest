package com.cst.wellnest.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cst.wellnest.utils.extensions.logErrorMessage

class FoodItemViewModel : ViewModel() {
    private val _foodItems = MutableLiveData<List<FoodItem>>()
    val foodItems: LiveData<List<FoodItem>> = _foodItems

    fun addItem(foodItem: FoodItem) {
        println("FoodItemViewModel - addItem")
        println(foodItem)
        val currentList = _foodItems.value ?: emptyList()
        _foodItems.value = currentList + foodItem
    }

    fun removeItem(position: Int) {
        println("Removed from pos")
        println(position)

        println("Initial List")
        println(foodItems.value)
        "Remove Item" + position.toString().logErrorMessage()
        val currentList = _foodItems.value?.toMutableList() ?: return
        if (position in currentList.indices) {
            currentList.removeAt(position)
            _foodItems.value = currentList
        }
        println("Changed list")
        println(foodItems.value)
    }

    fun getTotalCalories(): FoodItem {
        val foodItemsList = _foodItems.value ?: emptyList()

        var totalCalories = 0
        var totalProtein = 0
        var totalCarbs = 0
        var totalFats = 0

        for (item in foodItemsList) {
            totalCalories += item.caloriesPerQuantity
            totalProtein += item.proteinPerQuantity
            totalCarbs += item.carbsPerQuantity
            totalFats += item.fatsPerQuantity
        }

        return FoodItem("Total", totalCalories, totalProtein, totalCarbs, totalFats, )
    }


}