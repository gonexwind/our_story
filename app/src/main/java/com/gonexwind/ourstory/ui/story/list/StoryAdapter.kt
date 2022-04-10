package com.gonexwind.ourstory.ui.story.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.core.source.model.Story
import com.gonexwind.ourstory.databinding.ItemStoryBinding

class StoryAdapter(private val stories: List<Story>) :
    RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            binding.apply {
                nameTextView.text = story.name
                descriptionTextView.text = story.description
                storyImageView.load(story.photoUrl) {
                    placeholder(R.drawable.loading_animation)
                }
                storyCard.setOnClickListener {
                    val toDetail =
                        ListStoryFragmentDirections.actionListStoryFragmentToDetailStoryFragment(
                            story
                        )
                    it.findNavController().navigate(toDetail)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        stories[position].let { holder.bind(it) }
    }

    override fun getItemCount(): Int = stories.size
}