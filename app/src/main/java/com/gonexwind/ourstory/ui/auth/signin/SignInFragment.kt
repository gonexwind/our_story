package com.gonexwind.ourstory.ui.auth.signin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.data.remote.response.LoginResponse
import com.gonexwind.ourstory.data.remote.retrofit.ApiConfig
import com.gonexwind.ourstory.databinding.FragmentSignInBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.signInButton.setOnClickListener {
            login()
//            findNavController().navigate(R.id.action_signInFragment_to_listStoryFragment)
        }
    }

    private fun login() {
        ApiConfig.service().login(
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString(),
        ).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                val result = response.body()!!.loginResult
                Log.d("BERHASIL", result.name)
                Log.d("BERHASIL", result.token)
                Log.d("BERHASIL", result.userId)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("ERROR", t.message.toString())
            }
        })
    }
}