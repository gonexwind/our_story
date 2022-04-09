package com.gonexwind.ourstory.ui.auth.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.databinding.FragmentSignInBinding
import com.gonexwind.ourstory.data.remote.request.LoginRequest

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SignInViewModel by viewModels()

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
            signIn(binding.emailEditText.text.toString(), binding.passwordEditText.text.toString())
            findNavController().navigate(R.id.action_signInFragment_to_listStoryFragment)
        }
    }

    private fun signIn(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        viewModel.signIn(loginRequest)
    }
}