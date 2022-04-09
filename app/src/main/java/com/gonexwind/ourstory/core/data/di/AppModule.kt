package com.gonexwind.ourstory.core.data.di

import com.gonexwind.ourstory.core.data.source.local.LocalDataSource
import com.gonexwind.ourstory.core.data.source.remote.RemoteDataSource
import com.gonexwind.ourstory.core.data.source.remote.network.ApiConfig
import org.koin.dsl.module

val appModule = module {
    single { ApiConfig.provideApiService }
    single { RemoteDataSource(get()) }
    single { LocalDataSource() }
}