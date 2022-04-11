package com.gonexwind.ourstory.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonexwind.ourstory.core.repository.AppRepository
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.request.PostStoryRequest
import com.gonexwind.ourstory.core.source.remote.response.PostResponse
import com.gonexwind.ourstory.core.source.remote.response.StoriesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    fun getAllStories(token: String): LiveData<ApiState<StoriesResponse>> {
        val result = MutableLiveData<ApiState<StoriesResponse>>()
        viewModelScope.launch {
            appRepository.getAllStories(token).collect {
                result.postValue(it)
            }
        }
        return result
    }

    fun postStory(
        token: String,
        postStoryRequest: PostStoryRequest
    ): LiveData<ApiState<PostResponse>> {
        val result = MutableLiveData<ApiState<PostResponse>>()
        viewModelScope.launch {
            appRepository.postStory(token, postStoryRequest).collect {
                result.postValue(it)
            }
        }
        return result
    }
}