package com.cst.wellnest.networking.models

data class LoginAPIRequestModel(
    val email: String,
    val password: String
)

data class LoginAPIResponseModel(
    val token: String
)