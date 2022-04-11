package com.gonexwind.ourstory.ui.story.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.databinding.FragmentListStoryBinding
import com.gonexwind.ourstory.ui.story.StoryViewModel
import com.gonexwind.ourstory.utils.UserPrefs
import com.gonexwind.ourstory.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListStoryFragment : Fragment() {
    private var _binding: FragmentListStoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StoryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPrefs = UserPrefs(requireContext())
        getAllStories("Bearer ${userPrefs.getToken}")

        binding.apply {
            headerTextView.text = getString(R.string.greet_user, userPrefs.username.toString())
            writeButton.setOnClickListener {
                findNavController().navigate(R.id.action_listStoryFragment_to_addStoryFragment)
            }
            signOutButton.setOnClickListener {
                val dialogBuilder = AlertDialog.Builder(requireContext())
                dialogBuilder.setMessage(getString(R.string.exit_message))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                        userPrefs.clearToken()
                        findNavController().navigate(R.id.action_listStoryFragment_to_signInFragment)
                        toast(requireContext(), getString(R.string.bye))
                    }
                    .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                        dialog.cancel()
                    }
                val alert = dialogBuilder.create()
                alert.setTitle(getString(R.string.sign_out))
                alert.show()
            }
        }
    }

    private fun getAllStories(token: String) {
        viewModel.getAllStories(token).observe(viewLifecycleOwner) {
            when (it) {
                is ApiState.Loading -> {
                    showLoading(true)
                }
                is ApiState.Success -> {
                    showLoading(false)
                    val adapter = StoryAdapter(it.data.listStory)
                    binding.storyRecyclerView.adapter = adapter
                }
                is ApiState.Error -> {
                    showLoading(false)
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.storyRecyclerView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.storyRecyclerView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }
}