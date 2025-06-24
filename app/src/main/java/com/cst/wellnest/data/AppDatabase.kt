package com.cst.wellnest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cst.wellnest.data.dao.UserDao
import com.cst.wellnest.models.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}