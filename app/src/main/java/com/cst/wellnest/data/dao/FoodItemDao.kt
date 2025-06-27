package com.cst.wellnest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cst.wellnest.models.FoodItem

@Dao
interface FoodItemDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: FoodItem): Long

    @Query("SELECT * FROM food_item")
    fun getAll(): List<FoodItem>

    @Query("DELETE FROM food_item WHERE id = :foodItemId")
    suspend fun deleteFoodItemById(foodItemId: Long)
}