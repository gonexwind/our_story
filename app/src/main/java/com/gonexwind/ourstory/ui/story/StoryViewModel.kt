package com.gonexwind.ourstory.ui.story

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonexwind.ourstory.core.repository.AppRepository
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.response.PostResponse
import com.gonexwind.ourstory.core.source.remote.response.StoriesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(private val appRepository: AppRepository) :
    ViewModel() {

    fun getAllStories(token: String, page: Int?, size: Int?): LiveData<ApiState<StoriesResponse>> {
        val result = MutableLiveData<ApiState<StoriesResponse>>()
        viewModelScope.launch {
            appRepository.getAllStories(token, page, size).collect {
                result.postValue(it)
            }
        }
        return result
    }

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