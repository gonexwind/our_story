package com.gonexwind.ourstory.data.remote.retrofit

import com.gonexwind.ourstory.network.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

private const val BASE_URL = "https://story-api.dicoding.dev/v1"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @POST("/login")
    suspend fun login()

    @POST("/register")
    suspend fun register()

    @GET("/stories")
    suspend fun getStories(): List<User>

    @POST("/stories")
    suspend fun addStory()
}

object StoryApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}