package com.gonexwind.ourstory.ui.map

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.gonexwind.ourstory.DataDummy
import com.gonexwind.ourstory.core.repository.AppRepository
import com.gonexwind.ourstory.core.source.remote.network.ApiState
import com.gonexwind.ourstory.core.source.remote.response.StoriesResponse
import com.gonexwind.ourstory.getOrAwaitValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MapsViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var appRepository: AppRepository
    private lateinit var viewModel: MapsViewModel
    private val dummyStoriesResponse = DataDummy.generateDummyStoryResponse()
    private val token = DataDummy.token
    private val scope = CoroutineScope(Dispatchers.IO)

    @Before
    fun setUp() {
        viewModel = MapsViewModel(appRepository)
    }

    @Test
    fun `when Get Map Stories Should Not Null and Return Success`() =
        runBlockingTest {
            scope.launch {
                val expectedMapStories = MutableLiveData<ApiState<StoriesResponse>>()
                expectedMapStories.value = ApiState.Success(dummyStoriesResponse)
                `when`(viewModel.getMapStories(token)).thenReturn(expectedMapStories)
                val actualMapStories = viewModel.getMapStories(token).getOrAwaitValue()
                Mockito.verify(appRepository).getMapStories(token)
                Assert.assertNotNull(actualMapStories)
                Assert.assertTrue(actualMapStories is ApiState.Success)
                Assert.assertEquals(
                    dummyStoriesResponse.listStory.size,
                    (actualMapStories as ApiState.Success).data.listStory.size
                )
            }
        }

    @Test
    fun `when Network Error Should Return Error`() =
        runBlockingTest {
            scope.launch {
                val expectedMapStories = MutableLiveData<ApiState<StoriesResponse>>()
                expectedMapStories.value = ApiState.Error("Error")
                `when`(viewModel.getMapStories(token)).thenReturn(expectedMapStories)
                val actualMapStories = viewModel.getMapStories(token).getOrAwaitValue()
                Mockito.verify(appRepository).getMapStories(token)
                Assert.assertNotNull(actualMapStories)
                Assert.assertTrue(actualMapStories is ApiState.Error)
            }
        }
}