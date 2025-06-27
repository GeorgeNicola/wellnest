package com.cst.wellnest.networking.api

import com.cst.wellnest.networking.models.LoginAPIRequestModel
import com.cst.wellnest.networking.models.LoginAPIResponseModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthenticationApiService {
    @POST("/api/login")
    @Headers("x-api-key: reqres-free-v1")
    suspend fun login(@Body loginRequest: LoginAPIRequestModel): LoginAPIResponseModel
}