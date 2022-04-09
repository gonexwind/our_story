package com.gonexwind.ourstory.data.remote.response

import com.gonexwind.ourstory.data.model.User
import com.squareup.moshi.Json

data class LoginResponse(
    @Json val error: Boolean,
    @Json val message: String,
    @Json val loginResult: User,
)