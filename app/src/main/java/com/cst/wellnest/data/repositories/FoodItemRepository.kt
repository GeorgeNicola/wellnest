package com.cst.wellnest.data.repositories

import com.cst.wellnest.ApplicationController
import com.cst.wellnest.data.dao.FoodItemDao
import com.cst.wellnest.models.FoodItem

object FoodItemRepository {
    suspend fun insertFoodItem(item: FoodItem): Long? =
        ApplicationController.instance?.appDatabase?.foodItemDao?.insert(item)

    fun getAllFoodItems(): List<FoodItem>? =
        ApplicationController.instance?.appDatabase?.foodItemDao?.getAll()
}