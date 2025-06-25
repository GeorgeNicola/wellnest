package com.cst.wellnest.networking.api

import com.cst.wellnest.models.User
import com.cst.wellnest.networking.models.LoginAPIRequestModel
import com.cst.wellnest.networking.models.LoginAPIResponseModel
import com.cst.wellnest.networking.models.RegisterAPIRequestModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationApiService {
    @POST("/api/login")
    @Headers("x-api-key: reqres-free-v1")
    suspend fun login(@Body loginRequest: LoginAPIRequestModel): Result<LoginAPIResponseModel>

    @POST("/api/register")
    @Headers("x-api-key: reqres-free-v1")
    suspend fun register(@Body registerRequest: RegisterAPIRequestModel): Result<User>
}