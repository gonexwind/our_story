package com.gonexwind.ourstory.ui.story.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gonexwind.ourstory.R
import com.gonexwind.ourstory.core.source.model.Story
import com.gonexwind.ourstory.databinding.ItemStoryBinding
import com.gonexwind.ourstory.utils.Utils

class StoryAdapter :
    PagingDataAdapter<Story, StoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Story>() {
            override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


    inner class ViewHolder(private val binding: ItemStoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: Story) {
            binding.apply {
                nameTextView.text = story.name
                descriptionTextView.text = story.description
                createdAtTextView.text = Utils.formatDate(story.createdAt, "Asia/Jakarta")
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
        val data = getItem(position)
        if (data != null) {
            holder.bind(data)
        }
    }
}