package com.gonexwind.ourstory.core.data.repository

import android.util.Log
import com.gonexwind.ourstory.core.data.source.local.LocalDataSource
import com.gonexwind.ourstory.core.data.source.remote.RemoteDataSource
import com.gonexwind.ourstory.core.data.source.remote.network.Resource
import com.gonexwind.ourstory.core.data.source.remote.request.LoginRequest
import kotlinx.coroutines.flow.flow

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) {
    fun login(data: LoginRequest) = flow {
        emit(Resource.loading(null))
        try {
            remote.login(data).let {
                if (it.isSuccessful) {
                    val body = it.body()
                    val user = body?.loginResult
                    emit(Resource.success(user))
                    Log.d("SUCCESS: ", body.toString())
                } else {
                    emit(Resource.error("ERROR", null))
                }
            }
        } catch (e: Exception) {
            Log.e("LOGIN ERROR", e.message.toString())
        }
    }
}