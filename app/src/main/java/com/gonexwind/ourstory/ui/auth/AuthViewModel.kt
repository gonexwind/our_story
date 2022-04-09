package com.gonexwind.ourstory.ui.auth

import androidx.lifecycle.*
import com.gonexwind.ourstory.core.repository.AppRepository
import com.gonexwind.ourstory.core.source.remote.request.LoginRequest
import com.gonexwind.ourstory.core.source.remote.response.ApiResponse
import com.gonexwind.ourstory.core.source.remote.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val repo: AppRepository) : ViewModel() {
    fun login(data: LoginRequest): LiveData<ApiResponse<LoginResponse>> {
        val result = MutableLiveData<ApiResponse<LoginResponse>>()
        viewModelScope.launch {
            repo.login(data).collect { result.postValue(it) }
        }
        return result
    }
}