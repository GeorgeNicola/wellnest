package com.cst.wellnest.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cst.wellnest.models.FoodItem
import com.cst.wellnest.models.FoodItemListByDay
import java.util.Date

@Dao
interface FoodItemListByDayDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(entry: FoodItemListByDay): Long

    @Query("SELECT * FROM food_item_list_by_day")
    fun getAll(): List<FoodItemListByDay>

    @Query("""
        SELECT f.* FROM food_item f
        JOIN   food_item_list_by_day l ON f.id = l.food_id
        WHERE  l.user_id = :userId AND l.selection_date = :date
    """)
    suspend fun getFoodItemsForUserOnDate(
        userId: Int,
        date: String
    ): List<FoodItem>
}