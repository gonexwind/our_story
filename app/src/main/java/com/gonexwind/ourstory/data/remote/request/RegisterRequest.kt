package com.gonexwind.ourstory.data.remote.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
)