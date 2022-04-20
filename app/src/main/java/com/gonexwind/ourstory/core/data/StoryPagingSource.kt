package com.gonexwind.ourstory.core.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.gonexwind.ourstory.core.source.model.Story
import com.gonexwind.ourstory.core.source.remote.network.ApiService

class StoryPagingSource(private val apiService: ApiService, val token: String) :
    PagingSource<Int, Story>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Story>): Int? = state.anchorPosition?.let {
        val anchorPage = state.closestPageToPosition(it)
        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getAllStories(token, position, params.loadSize)

            LoadResult.Page(
                data = responseData.listStory,
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.listStory.isNullOrEmpty()) null else position + 1,
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}