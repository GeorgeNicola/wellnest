package com.cst.wellnest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cst.wellnest.models.FoodItemListByDay

@Dao
interface FoodItemListByDayDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entry: FoodItemListByDay): Long

    @Query("SELECT * FROM food_item_list_by_day")
    fun getAll(): List<FoodItemListByDay>
}