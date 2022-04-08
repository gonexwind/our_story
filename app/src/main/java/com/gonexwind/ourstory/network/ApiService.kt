package com.gonexwind.ourstory.network

import com.gonexwind.ourstory.model.request.LoginRequest
import com.gonexwind.ourstory.model.User
import com.gonexwind.ourstory.model.request.AddStoryRequest
import com.gonexwind.ourstory.model.request.RegisterRequest
import com.gonexwind.ourstory.model.response.LoginResponse
import com.gonexwind.ourstory.model.response.PostResponse
import com.gonexwind.ourstory.utils.Constants.BASE_URL
import com.gonexwind.ourstory.utils.Constants.LOGIN_URL
import com.gonexwind.ourstory.utils.Constants.REGISTER_URL
import com.gonexwind.ourstory.utils.Constants.STORY_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @POST(LOGIN_URL)
    suspend fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST(REGISTER_URL)
    suspend fun register(@Body request: RegisterRequest): Call<PostResponse>

    @GET(STORY_URL)
    suspend fun getAllStories(): Call<List<User>>

    @POST(STORY_URL)
    @FormUrlEncoded
    suspend fun addStory(@Body request: AddStoryRequest): Call<PostResponse>
}

object StoryApi {
    val service: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}