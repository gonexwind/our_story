package com.gonexwind.ourstory.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonexwind.ourstory.core.repository.AppRepository
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.response.StoriesResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MapsViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    fun getMapStories(token: String): LiveData<ApiState<StoriesResponse>> {
        val result = MutableLiveData<ApiState<StoriesResponse>>()
        viewModelScope.launch {
            appRepository.getMapStories(token).collect {
                result.postValue(it)
            }
        }
        return result
    }
}