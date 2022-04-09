package com.gonexwind.ourstory.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.gonexwind.ourstory.core.data.repository.AppRepository
import com.gonexwind.ourstory.core.data.source.remote.request.LoginRequest

class AuthViewModel(private val repo: AppRepository): ViewModel() {
    fun login(data: LoginRequest) = repo.login(data).asLiveData()
}