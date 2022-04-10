package com.gonexwind.ourstory.core.source.datasource

import com.gonexwind.ourstory.core.source.remote.network.ApiService
import com.gonexwind.ourstory.core.source.remote.request.LoginRequest
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.request.RegisterRequest
import com.gonexwind.ourstory.core.source.remote.response.LoginResponse
import com.gonexwind.ourstory.core.source.remote.response.PostResponse
import com.gonexwind.ourstory.core.source.remote.response.StoriesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun login(loginRequest: LoginRequest): Flow<ApiState<LoginResponse>> = flow {
        try {
            emit(ApiState.Loading)
            val response = apiService.login(loginRequest)
            if (response.error) {
                emit(ApiState.Error(response.message))
            }
            emit(ApiState.Success(response))
        } catch (e: Exception) {
            emit(ApiState.Error(e.message.toString()))
        }
    }

    suspend fun register(registerRequest: RegisterRequest): Flow<ApiState<PostResponse>> = flow {
        try {
            emit(ApiState.Loading)
            val response = apiService.register(registerRequest)
            if (response.error) {
                emit(ApiState.Error(response.message))
            }
            emit(ApiState.Success(response))
        } catch (e: Exception) {
            emit(ApiState.Error(e.message.toString()))
        }
    }

    suspend fun getAllStories(token: String): Flow<ApiState<StoriesResponse>> = flow {
        try {
            emit(ApiState.Loading)
            val response = apiService.getAllStories(token)
            if (response.error) {
                emit(ApiState.Error(response.message))
            }
            emit(ApiState.Success(response))
        } catch (e: Exception) {
            emit(ApiState.Error(e.message.toString()))
        }
    }
}