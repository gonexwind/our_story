package com.gonexwind.ourstory.core.source.remote.response

import com.gonexwind.ourstory.core.source.model.Story

data class StoriesResponse(
    val error: Boolean,
    val message: String,
    val listStory: List<Story>,
)