package com.cst.wellnest.data.repositories

import android.content.SharedPreferences
import android.util.Log
import com.cst.wellnest.ApplicationController
import com.cst.wellnest.data.dao.UserDao
import com.cst.wellnest.models.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object UserRepository {
    suspend fun saveUser(user: User) = withContext(Dispatchers.IO) {
        try {
            ApplicationController.instance?.appDatabase?.userDao?.insert(user)
        } catch (e: Exception) {
            e.printStackTrace()
            e.message?.let { Log.e("tag", it) }
        }
    }

    suspend fun authenticate(
        email: String,
        password: String
    ): User? = withContext(Dispatchers.IO) {
        ApplicationController.instance?.appDatabase?.userDao?.getUserByEmailAndPassword(email, password)
    }

    suspend fun getUserByEmail(
        email: String,
    ): User? = withContext(Dispatchers.IO) {
        ApplicationController.instance?.appDatabase?.userDao?.getUserByEmail(email)
    }
}