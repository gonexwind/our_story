package com.gonexwind.ourstory.core.data.di

import com.gonexwind.ourstory.core.data.repository.AppRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRepository(get(), get()) }
}