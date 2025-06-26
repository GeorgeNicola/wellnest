package com.cst.wellnest.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_item")
data class FoodItem(
    val name: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int,
    val quantity: Int = 100,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
) {
    var caloriesPerQuantity = (quantity / 100) * calories
    var proteinPerQuantity = (quantity / 100) * protein
    var carbsPerQuantity = (quantity / 100) * carbs
    var fatsPerQuantity= (quantity / 100) * fats
}