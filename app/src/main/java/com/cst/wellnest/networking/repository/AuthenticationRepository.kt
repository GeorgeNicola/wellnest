package com.cst.wellnest.networking.repository

import com.cst.wellnest.models.User
import com.cst.wellnest.networking.api.AuthenticationApiService
import com.cst.wellnest.networking.client.RetrofitClient
import com.cst.wellnest.networking.models.LoginAPIRequestModel
import com.cst.wellnest.networking.models.LoginAPIResponseModel
import com.cst.wellnest.networking.models.RegisterAPIRequestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object AuthenticationRepository {
    private val authenticationApiService by lazy {
        RetrofitClient.nonAuthInstance.create(AuthenticationApiService::class.java)
    }
    suspend fun register(user: RegisterAPIRequestModel): Result<User> = withContext(Dispatchers.IO) {
        try {
            val response = authenticationApiService.register(user)
            if (response.isSuccess) {
                response.getOrNull()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty registration response"))
            } else {
                Result.failure(Exception("Registration failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun login(email: String, password: String): Result<LoginAPIResponseModel> = withContext(Dispatchers.IO) {
        try {
            val response = authenticationApiService.login(LoginAPIRequestModel(email, password))
            if (response.isSuccess) {
                response.getOrNull()?.let { Result.success(it) }
                    ?: Result.failure(Exception("Empty login response"))
            } else {
                Result.failure(Exception("Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}