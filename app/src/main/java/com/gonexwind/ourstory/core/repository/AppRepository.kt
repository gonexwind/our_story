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
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) {
    suspend fun login(loginRequest: LoginRequest): Flow<ApiState<LoginResponse>> =
        remoteDataSource.login(loginRequest).flowOn(Dispatchers.IO)

    suspend fun register(registerRequest: RegisterRequest): Flow<ApiState<PostResponse>> =
        remoteDataSource.register(registerRequest).flowOn(Dispatchers.IO)

    suspend fun getAllStories(token: String): Flow<ApiState<StoriesResponse>> =
        remoteDataSource.getAllStories(token).flowOn(Dispatchers.IO)

    suspend fun getMapStories(token: String): Flow<ApiState<StoriesResponse>> =
        remoteDataSource.getMapStories(token).flowOn(Dispatchers.IO)


    suspend fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody,
    ): Flow<ApiState<PostResponse>> =
        remoteDataSource.postStory(token, file, description).flowOn(Dispatchers.IO)
}