package com.gonexwind.ourstory.ui.auth.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.core.data.source.remote.network.State
import com.gonexwind.ourstory.core.data.source.remote.request.LoginRequest
import com.gonexwind.ourstory.databinding.FragmentSignInBinding
import com.gonexwind.ourstory.ui.auth.AuthViewModel

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
//            findNavController().navigate(R.id.action_signInFragment_to_listStoryFragment)
        }
    }

    private fun login() {
        if (binding.emailEditText.text.isEmpty()) return
        if (binding.passwordEditText.text!!.isEmpty()) return

        val body = LoginRequest(
            binding.emailEditText.text.toString(),
            binding.passwordEditText.text.toString(),
        )

        viewModel.login(body).observe(viewLifecycleOwner) {
            when (it.state) {
                State.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.signInButton.visibility = View.GONE
                }
                State.SUCCESS -> {
                    Toast.makeText(
                        requireContext(),
                        "Selamat datang ${it.data?.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                State.ERROR -> {
                    Toast.makeText(
                        requireContext(),
                        "error bosku",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

}