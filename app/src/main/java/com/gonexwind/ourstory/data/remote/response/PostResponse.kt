package com.gonexwind.ourstory.data.remote.response

import com.squareup.moshi.Json

class PostResponse(
    @Json val error: Boolean,
    @Json val message: String,
)