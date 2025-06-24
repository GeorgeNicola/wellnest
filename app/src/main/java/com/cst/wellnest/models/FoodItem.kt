package com.cst.wellnest.models

data class FoodItem(
    val name: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fats: Int,

    val quantity: Int = 100,
) {
    val caloriesPerQuantity = (quantity / 100) * calories
    val proteinPerQuantity = (quantity / 100) * protein
    val carbsPerQuantity = (quantity / 100) * carbs
    val fatsPerQuantity= (quantity / 100) * fats
}