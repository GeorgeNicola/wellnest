package com.cst.wellnest.networking.api

import com.cst.wellnest.networking.models.LoginAPIRequestModel
import com.cst.wellnest.networking.models.LoginAPIResponseModel
import com.cst.wellnest.networking.models.RegisterAPIRequestModel
import com.cst.wellnest.networking.models.RegisterAPIResponseModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationApiService {
    @POST("/api/login")
    @Headers("x-api-key: reqres-free-v1")
    suspend fun login(@Body loginRequest: LoginAPIRequestModel): LoginAPIResponseModel

    @POST("/api/register")
    @Headers("x-api-key: reqres-free-v1")
    suspend fun register(@Body registerRequest: RegisterAPIRequestModel): RegisterAPIResponseModel
}