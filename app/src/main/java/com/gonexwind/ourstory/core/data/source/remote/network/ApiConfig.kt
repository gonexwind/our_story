package com.gonexwind.ourstory.core.data.source.remote.network

import com.gonexwind.ourstory.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiConfig {
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