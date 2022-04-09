package com.gonexwind.ourstory.core.repository

import com.gonexwind.ourstory.core.source.remote.request.LoginRequest
import com.gonexwind.ourstory.core.source.remote.response.ApiResponse
import com.gonexwind.ourstory.core.source.remote.response.LoginResponse
import com.gonexwind.ourstory.core.source.remote.datasource.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val dataSource: RemoteDataSource) {
    suspend fun login(data: LoginRequest): Flow<ApiResponse<LoginResponse>> =
        dataSource.login(data).flowOn(Dispatchers.IO)
}