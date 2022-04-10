package com.gonexwind.ourstory.ui.story.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.databinding.FragmentDetailStoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailStoryFragment : Fragment() {
    private var _binding: FragmentDetailStoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailStoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.actionBar?.setDisplayHomeAsUpEnabled(true);

        val story = DetailStoryFragmentArgs.fromBundle(arguments as Bundle).story

        binding.apply {
            storyImageView.load(story.photoUrl) {
                placeholder(R.drawable.loading_animation)
            }
            nameTextView.text = story.name
            descriptionTextView.text = story.description
        }
    }
}