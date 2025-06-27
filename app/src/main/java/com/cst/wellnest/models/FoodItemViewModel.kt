package com.cst.wellnest.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cst.wellnest.data.repositories.FoodRepository
import com.cst.wellnest.utils.extensions.logErrorMessage
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class FoodItemViewModel : ViewModel() {
    private val _foodItems = MutableLiveData<List<FoodItem>>()
    val foodItems: LiveData<List<FoodItem>> = _foodItems


    // Date + methods
    private val _selectedDate = MutableLiveData<LocalDate>(LocalDate.now())
    val selectedDate: LiveData<LocalDate> = _selectedDate

    fun getFormattedDate(): String {
        val date = _selectedDate.value ?: LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.getDefault())
        return date.format(formatter)
    }
    fun setDateToday() {
        _selectedDate.value = LocalDate.now()
    }
    fun goToNextDay() {
        val current = _selectedDate.value ?: LocalDate.now()
        _selectedDate.value = current.plusDays(1)
    }
    fun goToPreviousDay() {
        val current = _selectedDate.value ?: LocalDate.now()
        _selectedDate.value = current.minusDays(1)
    }


    fun addItem(foodItem: FoodItem) {
        println("FoodItemViewModel - addItem")
        println(foodItem)
        val currentList = _foodItems.value ?: emptyList()
        _foodItems.value = currentList + foodItem

        saveItemForDay(foodItem)
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

    private fun saveItemForDay(foodItem: FoodItem){
        viewModelScope.launch {
            val userId = 1
            val localDate = _selectedDate.value ?: LocalDate.now()
            val utilsDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
            val sqlDate = java.sql.Date(utilsDate.time)

            FoodRepository.insertFoodAndLinkToDate(
                foodItem,
                userId,
                localDate
            )

            println("FoodItemViewModel - saveItemForDay")
            println(userId)
            println(sqlDate)
            println(foodItem)
        }
    }

    fun getItems() {
        viewModelScope.launch {
            val userId = 1
            val localDate = _selectedDate.value ?: LocalDate.now()
            val utilsDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
            val sqlDate = java.sql.Date(utilsDate.time)


            val items = FoodRepository.getFoodItemsForUserOnDate(
                userId,
                localDate
            ) ?: emptyList()
            _foodItems.postValue(items)

            println("FoodItemViewModel - getItems")
            println(userId)
            println(sqlDate)
            println(items)
        }
  }
}