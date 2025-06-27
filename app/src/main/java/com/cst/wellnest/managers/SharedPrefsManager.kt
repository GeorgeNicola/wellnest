package com.cst.wellnest.managers

import com.cst.wellnest.ApplicationController

object SharedPrefsManager {

    private const val KEY_AUTH_TOKEN = "auth_token"
    private const val KEY_EMAIL = "email"

    private val sharedPrefs
        get() = ApplicationController.instance?.sharedPrefs

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

    fun logoutUser() {
        removeToken()
        removeEmail()
    }

    private fun removeToken() = sharedPrefs?.let { sp ->
        with(sp.edit()) {
            remove(KEY_AUTH_TOKEN)
            apply()
        }
    }

    private fun removeEmail() = sharedPrefs?.let { sp ->
        with(sp.edit()) {
            remove(KEY_AUTH_TOKEN)
            apply()
        }
    }
}

fun SharedPrefsManager.isUserLoggedIn() = !this.getAuthToken().isNullOrEmpty()

