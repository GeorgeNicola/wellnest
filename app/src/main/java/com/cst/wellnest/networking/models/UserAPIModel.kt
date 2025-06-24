package com.cst.wellnest.networking.models

import com.cst.wellnest.models.User

data class UserListAPIResponseModel(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<User>
)