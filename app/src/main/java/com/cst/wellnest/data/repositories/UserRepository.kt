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

//    suspend fun getUser(): User? = withContext(Dispatchers.IO) {
//        userDao?.getUser()?.let {
//            return@withContext User(
//                email = it.email,
//                firstName = it.firstName,
//                lastName = it.lastName
//            )
//        }
//
//        prefs.getString(KEY_USER, null)?.let {
//            gson.fromJson(it, User::class.java)
//        }
//    }
}