package com.gonexwind.ourstory.core.data.source.remote.network

import com.gonexwind.ourstory.core.data.source.remote.request.LoginRequest
import com.gonexwind.ourstory.core.data.source.remote.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

//    @POST("register")
//    suspend fun register(@Body request: RegisterRequest): Call<PostResponse>
//
//    @GET("stories")
//    suspend fun getAllStories(
//        @Header("Authorization") token: String
//    ): Call<GetStoriesResponse>
//
//    @Multipart
//    @POST("stories")
//    suspend fun addStory(
//        @Part file: MultipartBody.Part,
//        @Part("description") description: RequestBody,
//    ): Call<PostResponse>
}