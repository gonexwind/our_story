package com.gonexwind.ourstory.ui.auth

import androidx.lifecycle.*
import com.gonexwind.ourstory.core.repository.AppRepository
import com.gonexwind.ourstory.core.source.remote.request.LoginRequest
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.request.RegisterRequest
import com.gonexwind.ourstory.core.source.remote.response.LoginResponse
import com.gonexwind.ourstory.core.source.remote.response.PostResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {
    fun login(loginRequest: LoginRequest): LiveData<ApiState<LoginResponse>> {
        val result = MutableLiveData<ApiState<LoginResponse>>()
        viewModelScope.launch {
            appRepository.login(loginRequest).collect { result.postValue(it) }
        }
        return result
    }

    fun register(registerRequest: RegisterRequest): LiveData<ApiState<PostResponse>> {
        val result = MutableLiveData<ApiState<PostResponse>>()
        viewModelScope.launch {
            appRepository.register(registerRequest).collect { result.postValue(it) }
        }
        return result
    }
}