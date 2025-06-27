package com.cst.wellnest.networking.repository

import com.cst.wellnest.networking.api.AuthenticationApiService
import com.cst.wellnest.networking.client.RetrofitClient
import com.cst.wellnest.networking.models.LoginAPIRequestModel

object AuthenticationRepository {
    private val authenticationApiService by lazy {
        RetrofitClient.nonAuthInstance.create(AuthenticationApiService::class.java)
    }

    suspend fun login(email: String, password: String) =
        authenticationApiService.login(LoginAPIRequestModel(email, password))
}