package com.gonexwind.ourstory.core.data.source.model

import com.squareup.moshi.Json

data class Story(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String,
    val createdAt: String,
)
