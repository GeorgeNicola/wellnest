package com.cst.wellnest.managers

import com.cst.wellnest.ApplicationController

object SharedPrefsManager {

    private const val KEY_AUTH_TOKEN = "auth_token"

    fun saveAuthToken(token: String) = sharedPrefs?.let { sp ->
        with(sp.edit()) {
            putString(KEY_AUTH_TOKEN, token)
            apply()
        }
    }

    fun getAuthToken(): String? = sharedPrefs?.getString(KEY_AUTH_TOKEN, null)

    fun removeToken() = sharedPrefs?.let { sp ->
        with(sp.edit()) {
            remove(KEY_AUTH_TOKEN)
            apply()
        }
    }

    private val sharedPrefs
        get() = ApplicationController.instance?.sharedPrefs
}

fun SharedPrefsManager.isUserLoggedIn() = !this.getAuthToken().isNullOrEmpty()

fun SharedPrefsManager.logoutUser() = removeToken()