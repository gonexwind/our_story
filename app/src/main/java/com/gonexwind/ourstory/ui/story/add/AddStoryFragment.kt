package com.gonexwind.ourstory.ui.story.add

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.gonexwind.ourstory.databinding.FragmentAddStoryBinding
import com.gonexwind.ourstory.ui.story.StoryViewModel
import com.gonexwind.ourstory.utils.UserPrefs
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class AddStoryFragment : Fragment() {
    private var _binding: FragmentAddStoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StoryViewModel by activityViewModels()
    private lateinit var prefs: UserPrefs
    private var getFile: File? = null
    private lateinit var currentPhotoPath: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prefs = UserPrefs(requireContext())
    }

}