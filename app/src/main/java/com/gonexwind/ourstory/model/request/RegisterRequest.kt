package com.gonexwind.ourstory.model.request

import com.squareup.moshi.Json

data class RegisterRequest(
    @Json val name: String,
    @Json val email: String,
    @Json val password: String,
)