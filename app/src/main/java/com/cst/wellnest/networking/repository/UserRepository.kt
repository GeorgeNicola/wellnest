package com.cst.wellnest.networking.repository

import android.content.SharedPreferences
import com.cst.wellnest.data.dao.UserDao
import com.cst.wellnest.models.User
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val prefs: SharedPreferences,
    private val gson: Gson,
    private val userDao: UserDao?
) {
    private val KEY_USER = "key_user"
    suspend fun saveUser(user: User) = withContext(Dispatchers.IO) {
        prefs.edit().putString(KEY_USER, gson.toJson(user)).apply()

        val entity = User(
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName
        )
        userDao?.insert(entity)
    }

    suspend fun getUser(): User? = withContext(Dispatchers.IO) {
        userDao?.getUser()?.let {
            return@withContext User(
                email = it.email,
                firstName = it.firstName,
                lastName = it.lastName
            )
        }

        prefs.getString(KEY_USER, null)?.let {
            gson.fromJson(it, User::class.java)
        }
    }
}