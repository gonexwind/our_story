package com.gonexwind.ourstory.ui.auth.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gonexwind.ourstory.data.remote.request.LoginRequest
import com.gonexwind.ourstory.data.remote.response.LoginResponse
import com.gonexwind.ourstory.network.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInViewModel : ViewModel() {
    fun signIn(loginRequest: LoginRequest) {
        viewModelScope.launch {
            ApiConfig.service.login(loginRequest).enqueue(object: Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val result = response.body()
                    Log.d("COBA TANYA HATIMU SEKALI LAGI", result!!.token)
                    Log.d("COBA TANYA HATIMU SEKALI LAGI", result.name)
                    Log.d("COBA TANYA HATIMU SEKALI LAGI", result.userId)
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.d("COBA TANYA HATIMU SEKALI LAGI", t.message.toString())
                }
            })
        }
    }
}
