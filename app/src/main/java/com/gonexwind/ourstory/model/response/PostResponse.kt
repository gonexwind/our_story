package com.gonexwind.ourstory.model.response

import com.squareup.moshi.Json

class PostResponse(
    @Json val error: Boolean,
    @Json val message: String,
)