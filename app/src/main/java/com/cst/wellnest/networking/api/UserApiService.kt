package com.cst.wellnest.networking.api

import com.cst.wellnest.networking.models.UserListAPIResponseModel
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserApiService {
    @Headers("x-api-key: reqres-free-v1")
    @GET("/api/users")
    suspend fun getUsers(@Query("page") page: Int): UserListAPIResponseModel
}