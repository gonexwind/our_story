package com.gonexwind.ourstory.ui.auth.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.request.RegisterRequest
import com.gonexwind.ourstory.databinding.FragmentSignUpBinding
import com.gonexwind.ourstory.ui.auth.AuthViewModel
import com.gonexwind.ourstory.utils.Constants
import com.gonexwind.ourstory.utils.Utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInTextButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.signUpButton.setOnClickListener {
            register()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun register() {
        val name = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val registerRequest = RegisterRequest(name, email, password)

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            val message = getString(R.string.please_fill_out)
            binding.apply {
                nameEditText.error = message
                emailEditText.error = message
                passwordEditText.error = message
            }
            toast(requireContext(), message)
            return
        }
        if (password.length < 6) return

        viewModel.register(registerRequest).observe(viewLifecycleOwner) {
            when (it) {
                is ApiState.Loading -> {
                    showLoading(true)
                }
                is ApiState.Success -> {
                    try {
                        showLoading(false)
                        toast(requireContext(), it.data.message)
                    } finally {
                        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                    }
                }
                is ApiState.Error -> {
                    toast(requireContext(), it.message)
                    Log.e(Constants.TAG_ERROR, it.message)
                    showLoading(false)
                }
            }
        }

    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.signUpButton.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.signUpButton.visibility = View.VISIBLE
        }
    }
}