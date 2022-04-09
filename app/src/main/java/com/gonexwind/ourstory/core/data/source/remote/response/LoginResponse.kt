package com.gonexwind.ourstory.core.data.source.remote.response

import com.gonexwind.ourstory.core.data.source.model.User

data class LoginResponse(
    val error: Boolean,
    val message: String,
    val loginResult: User,
)