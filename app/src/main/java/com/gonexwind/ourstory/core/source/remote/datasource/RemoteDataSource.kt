package com.gonexwind.ourstory.core.source.remote.datasource

import com.gonexwind.ourstory.core.source.remote.network.ApiService
import com.gonexwind.ourstory.core.source.remote.request.LoginRequest
import com.gonexwind.ourstory.core.source.remote.response.ApiResponse
import com.gonexwind.ourstory.core.source.remote.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val api: ApiService) {
    suspend fun login(data: LoginRequest): Flow<ApiResponse<LoginResponse>> = flow {
        try {
            emit(ApiResponse.Loading)
            val response = api.login(data)
            if (response.error) {
                emit(ApiResponse.Error(response.message))
            }
            emit(ApiResponse.Success(response))
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message.toString()))
        }
    }
}