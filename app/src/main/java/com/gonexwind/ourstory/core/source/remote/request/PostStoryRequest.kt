package com.gonexwind.ourstory.core.source.remote.request

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Part

data class PostStoryRequest(
    @Part val file: MultipartBody.Part,
    @Part("description") val description: RequestBody,
)