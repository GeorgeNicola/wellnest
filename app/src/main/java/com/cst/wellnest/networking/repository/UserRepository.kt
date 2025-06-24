package com.cst.wellnest.networking.repository

import com.cst.wellnest.networking.api.UserApiService
import com.cst.wellnest.networking.client.RetrofitClient

object UserRepository {
    private val userApiService by lazy {
        RetrofitClient.authInstance.create(UserApiService::class.java)
    }

    suspend fun getUsers(page: Int) = userApiService.getUsers(page = page)
}