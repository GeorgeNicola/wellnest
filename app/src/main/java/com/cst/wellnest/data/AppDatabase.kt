package com.cst.wellnest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cst.wellnest.data.dao.FoodItemDao
import com.cst.wellnest.data.dao.FoodItemListByDayDao
import com.cst.wellnest.data.dao.UserDao
import com.cst.wellnest.models.FoodItem
import com.cst.wellnest.models.FoodItemListByDay
import com.cst.wellnest.models.User

@Database(
    entities = [
        User::class,
        FoodItem::class,
        FoodItemListByDay::class
    ],
    version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val foodItemDao: FoodItemDao
    abstract val foodItemListByDayDao: FoodItemListByDayDao
}