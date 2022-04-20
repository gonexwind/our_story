package com.gonexwind.ourstory.core.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gonexwind.ourstory.core.data.StoryPagingSource
import com.gonexwind.ourstory.core.source.model.Story
import com.gonexwind.ourstory.core.source.remote.network.ApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoryRepository @Inject constructor(private val apiService: ApiService) {
    fun getAllStories(token: String): Flow<PagingData<Story>> = Pager(
        config = PagingConfig(pageSize = 5),
        pagingSourceFactory = { StoryPagingSource(apiService, token) }
    ).flow
}