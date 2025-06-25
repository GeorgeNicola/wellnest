package com.cst.wellnest.networking.models

import com.cst.wellnest.models.User

data class RegisterAPIRequestModel(
    val email: String,
    val password: String,
    val firstName: String,
    var lastName: String
)

data class RegisterAPIResponseModel(
    val user: User
)