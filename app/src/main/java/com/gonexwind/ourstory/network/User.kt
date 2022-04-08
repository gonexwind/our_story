package com.gonexwind.ourstory.network

import com.squareup.moshi.Json

data class User(
    val id: String,
    @Json val name: String,
    @Json val description: String,
    @Json(name = "photoUrl") val photo: String,
    @Json(name = "createdAt") val date: String,
)