package com.cst.wellnest.data.repositories

import com.cst.wellnest.ApplicationController
import com.cst.wellnest.models.FoodItem
import com.cst.wellnest.models.FoodItemListByDay
import java.sql.Date
import java.time.LocalDate

object FoodRepository {
    suspend fun insertFoodAndLinkToDate(
        foodItem: FoodItem,
        userId: Int,
        date: Date
    ): Boolean {
        val foodId = FoodItemRepository.insertFoodItem(foodItem)?.toInt() ?: return false

        val entry = FoodItemListByDay(
            userId = userId,
            foodId = foodId,
            selectionDate = Date.valueOf(date.toString())
        )
        val entryId = FoodItemListByDayRepository.insertEntry(entry)
        return entryId != null
    }

    suspend fun getFoodItemsForUserOnDate(
        userId: Int,
        date: Date
    ): List<FoodItem>? {
        println("FoodRepository - getFoodItemsForUserOnDate")
        println("date")
        println(date)
        println("Date.valueOf(date.toString()")
        println(Date.valueOf(date.toString()))

        return ApplicationController.instance
            ?.appDatabase
            ?.foodItemListByDayDao
            ?.getFoodItemsForUserOnDate(userId, Date.valueOf(date.toString()))
    }
}