package com.gonexwind.ourstory.core.repository

import com.gonexwind.ourstory.core.source.remote.request.LoginRequest
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.response.LoginResponse
import com.gonexwind.ourstory.core.source.datasource.RemoteDataSource
import com.gonexwind.ourstory.core.source.remote.request.RegisterRequest
import com.gonexwind.ourstory.core.source.remote.response.PostResponse
import com.gonexwind.ourstory.core.source.remote.response.StoriesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val dataSource: RemoteDataSource) {
    suspend fun login(loginRequest: LoginRequest): Flow<ApiState<LoginResponse>> =
        dataSource.login(loginRequest).flowOn(Dispatchers.IO)

    suspend fun register(registerRequest: RegisterRequest): Flow<ApiState<PostResponse>> =
        dataSource.register(registerRequest).flowOn(Dispatchers.IO)

    suspend fun getAllStories(token: String): Flow<ApiState<StoriesResponse>> =
        dataSource.getAllStories(token).flowOn(Dispatchers.IO)
}