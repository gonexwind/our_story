package com.gonexwind.ourstory.data.remote.response

import com.gonexwind.ourstory.data.model.Story
import com.squareup.moshi.Json

data class GetStoriesResponse(
    @Json val error: Boolean,
    @Json val message: String,
    @Json val listStory: List<Story>,
)