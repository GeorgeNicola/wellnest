package com.cst.wellnest.networking.models

data class RegisterAPIRequestModel(
    val email: String,
    val password: String,
    val firstName: String,
    var lastName: String
)

data class RegisterAPIResponseModel(
    val success: Boolean
)