package com.gonexwind.ourstory.ui.story

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.gonexwind.ourstory.DataDummy
import com.gonexwind.ourstory.core.repository.AppRepository
import com.gonexwind.ourstory.core.source.model.Story
import com.gonexwind.ourstory.getOrAwaitValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StoryViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository
    private lateinit var storyViewModel: StoryViewModel
    private val dummyStory = DataDummy.generateDummyStory()
    private val token = DataDummy.token
    private val scope = CoroutineScope(Dispatchers.IO)

    @Before
    fun setUp() {
        storyViewModel = StoryViewModel(appRepository)
    }

    @Test
    fun `when Get Stories Should Not Null and Return Success`() = runBlockingTest {
        scope.launch {
            val expectedStory = MutableLiveData<PagingData<Story>>()
            expectedStory.value = PagingData.from(dummyStory)
            `when`(storyViewModel.getStoriesWithPage(token)).thenReturn(expectedStory)
            val actualStory = storyViewModel.getStoriesWithPage(token).getOrAwaitValue()
            Mockito.verify(appRepository).getStoriesWithPage(token)
            Assert.assertNotNull(actualStory)
        }
    }
}