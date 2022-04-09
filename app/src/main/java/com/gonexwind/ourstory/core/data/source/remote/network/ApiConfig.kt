package com.gonexwind.ourstory.core.data.source.remote.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiConfig {
    private const val BASE_URL = "https://story-api.dicoding.dev/v1/"
    private val client: Retrofit
        get() {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }

    val provideApiService: ApiService
        get() = client.create(ApiService::class.java)
}