package com.gonexwind.ourstory.data.model

import com.squareup.moshi.Json

data class Story(
    @Json val id: String,
    @Json val name: String,
    @Json val description: String,
    @Json(name = "photoUrl") val photo: String,
    @Json(name = "createdAt") val date: String,
)