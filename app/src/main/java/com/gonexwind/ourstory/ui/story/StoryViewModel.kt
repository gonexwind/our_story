package com.gonexwind.ourstory.ui.story

import androidx.lifecycle.ViewModel
import com.gonexwind.ourstory.core.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(private val repo: AppRepository): ViewModel() {


}