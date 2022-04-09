package com.gonexwind.ourstory.data.remote.request

import com.squareup.moshi.Json

data class LoginRequest(
    @Json val email: String,
    @Json val password: String,
)