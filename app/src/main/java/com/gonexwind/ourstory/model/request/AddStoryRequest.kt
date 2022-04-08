package com.gonexwind.ourstory.model.request

import com.squareup.moshi.Json

data class AddStoryRequest(
    @Json val description: String,
    @Json val photo: String,
)