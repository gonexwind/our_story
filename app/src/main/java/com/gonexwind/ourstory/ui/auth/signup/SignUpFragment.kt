package com.gonexwind.ourstory.ui.auth.signup

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.request.RegisterRequest
import com.gonexwind.ourstory.databinding.FragmentSignUpBinding
import com.gonexwind.ourstory.ui.auth.AuthViewModel

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

        binding.signInButton.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.signUpButton.setOnClickListener {
            register()
        }
    }

    private fun register() {
        val name = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) return

        val registerRequest = RegisterRequest(name, email, password)

        viewModel.register(registerRequest).observe(viewLifecycleOwner) {
            when (it) {
                is ApiState.Loading -> {
                    showLoading(true)
                }
                is ApiState.Success -> {
                    try {
                        showLoading(false)
                        Toast.makeText(
                            requireContext(),
                            it.data.message,
                            Toast.LENGTH_SHORT
                        ).show()

                    } finally {
                        findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
                    }
                }
                is ApiState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("ERROR BOSKU", it.message)
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