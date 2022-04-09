package com.gonexwind.ourstory.core.data.source.remote.network

import com.gonexwind.ourstory.data.remote.request.LoginRequest
import com.gonexwind.ourstory.data.remote.request.RegisterRequest
import com.gonexwind.ourstory.data.remote.response.GetStoriesResponse
import com.gonexwind.ourstory.data.remote.response.LoginResponse
import com.gonexwind.ourstory.data.remote.response.PostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<LoginResponse>

    @POST("register")
    suspend fun register(@Body request: RegisterRequest): Call<PostResponse>

    @GET("stories")
    suspend fun getAllStories(
        @Header("Authorization") token: String
    ): Call<GetStoriesResponse>

    @Multipart
    @POST("stories")
    suspend fun addStory(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody,
    ): Call<PostResponse>
}