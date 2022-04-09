package com.gonexwind.ourstory.utils

import android.app.Application
import com.gonexwind.ourstory.core.data.di.appModule
import com.gonexwind.ourstory.core.data.di.repositoryModule
import com.gonexwind.ourstory.core.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(appModule, viewModelModule, repositoryModule))
        }
    }
}