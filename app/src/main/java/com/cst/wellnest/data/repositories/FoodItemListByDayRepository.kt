package com.cst.wellnest.data.repositories

import com.cst.wellnest.ApplicationController
import com.cst.wellnest.models.FoodItemListByDay

class FoodItemListByDayRepository {
    suspend fun insertEntry(entry: FoodItemListByDay): Long? =
        ApplicationController.instance?.appDatabase?.foodItemListByDayDao?.insert(entry)

    fun getAllEntries(): List<FoodItemListByDay>? =
        ApplicationController.instance?.appDatabase?.foodItemListByDayDao?.getAll()
}