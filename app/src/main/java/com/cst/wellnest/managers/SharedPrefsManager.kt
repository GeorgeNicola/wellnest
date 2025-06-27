package com.cst.wellnest.managers

import com.cst.wellnest.ApplicationController

object SharedPrefsManager {

    private const val KEY_AUTH_TOKEN = "auth_token"
    private const val KEY_EMAIL = "email"

    fun saveAuthToken(token: String) = sharedPrefs?.let { sp ->
        with(sp.edit()) {
            putString(KEY_AUTH_TOKEN, token)
            apply()
        }
    }

    fun saveEmail(token: String) = sharedPrefs?.let { sp ->
        with(sp.edit()) {
            putString(KEY_EMAIL, token)
            apply()
        }
    }

    fun getAuthToken(): String? = sharedPrefs?.getString(KEY_AUTH_TOKEN, null)

    fun getEmail(): String? = sharedPrefs?.getString(KEY_EMAIL, null)

    fun removeToken() = sharedPrefs?.let { sp ->
        with(sp.edit()) {
            remove(KEY_AUTH_TOKEN)
            apply()
        }
    }

    fun removeEmail() = sharedPrefs?.let { sp ->
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