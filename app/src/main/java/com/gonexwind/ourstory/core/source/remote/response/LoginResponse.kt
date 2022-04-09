package com.gonexwind.ourstory.core.source.remote.response

import com.gonexwind.ourstory.core.source.model.User

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val loginResult: User,
)