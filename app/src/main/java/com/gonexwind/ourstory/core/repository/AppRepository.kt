package com.gonexwind.ourstory.core.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.gonexwind.ourstory.core.data.StoryPagingSource
import com.gonexwind.ourstory.core.source.datasource.RemoteDataSource
import com.gonexwind.ourstory.core.source.model.Story
import com.gonexwind.ourstory.core.source.remote.network.ApiService
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.request.LoginRequest
import com.gonexwind.ourstory.core.source.remote.request.RegisterRequest
import com.gonexwind.ourstory.core.source.remote.response.LoginResponse
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
class AppRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val apiService: ApiService
) {
    suspend fun login(loginRequest: LoginRequest): Flow<ApiState<LoginResponse>> =
        remoteDataSource.login(loginRequest).flowOn(Dispatchers.IO)

    suspend fun register(registerRequest: RegisterRequest): Flow<ApiState<PostResponse>> =
        remoteDataSource.register(registerRequest).flowOn(Dispatchers.IO)

    fun getStoriesWithPage(token: String): LiveData<PagingData<Story>> = Pager(
        config = PagingConfig(pageSize = 5),
        pagingSourceFactory = { StoryPagingSource(apiService, token) }
    ).liveData

    suspend fun getMapStories(token: String): Flow<ApiState<StoriesResponse>> =
        remoteDataSource.getMapStories(token).flowOn(Dispatchers.IO)

    suspend fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody,
    ): Flow<ApiState<PostResponse>> =
        remoteDataSource.postStory(token, file, description).flowOn(Dispatchers.IO)
}