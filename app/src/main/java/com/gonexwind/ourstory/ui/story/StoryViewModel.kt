package com.gonexwind.ourstory.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gonexwind.ourstory.core.repository.AppRepository
import com.gonexwind.ourstory.core.source.model.Story
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.response.PostResponse
import com.gonexwind.ourstory.core.source.remote.response.StoriesResponse
import com.gonexwind.ourstory.utils.UserPrefs
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(private val appRepository: AppRepository) :
    ViewModel() {
    fun getStoriesWithPage(token: String): LiveData<PagingData<Story>> =
        appRepository.getStoriesWithPage(token).cachedIn(viewModelScope)

    fun postStory(
        token: String,
        file: MultipartBody.Part,
        description: RequestBody,
    ): LiveData<ApiState<PostResponse>> {
        val result = MutableLiveData<ApiState<PostResponse>>()
        viewModelScope.launch {
            appRepository.postStory(token, file, description).collect {
                result.postValue(it)
            }
        }
        return result
    }
}