package com.gonexwind.ourstory.model.request

import com.squareup.moshi.Json

data class LoginRequest(
    @Json val email: String,
    @Json val password: String,
)