package com.gonexwind.ourstory.ui.story.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gonexwind.ourstory.core.repository.StoryRepository
import com.gonexwind.ourstory.core.source.model.Story
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ListStoryViewModel @Inject constructor(private val storyRepository: StoryRepository) :
    ViewModel() {
//    private val _story = MutableLiveData<List<Story>>()
//    val story = storyRepository.getAllStories(token).cachedIn(viewModelScope)

    fun getAllStories(token: String): Flow<PagingData<Story>> {
        return storyRepository.getAllStories(token).cachedIn(viewModelScope)
    }
}