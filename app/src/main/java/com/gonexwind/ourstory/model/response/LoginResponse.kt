package com.gonexwind.ourstory.model.response

import com.squareup.moshi.Json

data class LoginResponse(
    @Json val userId: String,
    @Json val name: String,
    @Json val token: String,
)