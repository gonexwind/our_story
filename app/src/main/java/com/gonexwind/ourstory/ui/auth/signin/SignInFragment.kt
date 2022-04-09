package com.gonexwind.ourstory.ui.auth.signin

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.core.source.remote.request.LoginRequest
import com.gonexwind.ourstory.core.source.remote.response.ApiResponse
import com.gonexwind.ourstory.databinding.FragmentSignInBinding
import com.gonexwind.ourstory.ui.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by activityViewModels()

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
        }
    }

    private fun login() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) return

        val data = LoginRequest(email, password)

        viewModel.login(data).observe(viewLifecycleOwner) {
            when (it) {
                is ApiResponse.Loading -> {
                    showLoading(true)
                }
                is ApiResponse.Success -> {
                    try {
                        val user = it.data.loginResult
                        Toast.makeText(
                            requireContext(),
                            "Selamat datang ${user.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } finally {
                        findNavController().navigate(R.id.action_signInFragment_to_listStoryFragment)
                    }
                }
                is ApiResponse.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "error bosku " + it.errorMessage,
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("ERROR BOSKU", it.errorMessage)
                    showLoading(false)
                }
            }
        }
    }

    private fun showLoading(isLoading : Boolean) {
        if(isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.signInButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.signInButton.visibility = View.VISIBLE
        }
    }

}