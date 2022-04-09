package com.gonexwind.ourstory.data.model

import com.squareup.moshi.Json

data class User(
    @Json val userId: String,
    @Json val name: String,
    @Json val token: String,
)