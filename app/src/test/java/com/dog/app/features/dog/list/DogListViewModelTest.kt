package com.dog.app.features.dog.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appmattus.kotlinfixture.kotlinFixture
import com.dog.app.features.getOrAwaitValue
import com.dog.domain.model.Dog
import com.dog.domain.model.Resource
import com.dog.domain.usecase.GetDogListUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.coroutines.cancellation.CancellationException

@ExperimentalCoroutinesApi
class DogListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val mockGetDogUseCase: GetDogListUseCase = mockk()
    private val fixture = kotlinFixture()

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given vm initialised When dog list return success Then ViewState should be success`() {
        //Given
        val expectedResult = Resource.Success(fixture<List<Dog>>())
        coEvery { mockGetDogUseCase() } returns (expectedResult)

        //when
        val homeViewModel = DogListViewModel(mockGetDogUseCase)

        //then
        coVerify(exactly = 1) { mockGetDogUseCase() }

        assertThat(homeViewModel.viewState.getOrAwaitValue()).isEqualTo(expectedResult)
    }

    @Test
    fun `Given vm initialised When dog list return error Then ViewState should be error`() {
        //Given
        val expectedError = Resource.Error(CancellationException())
        coEvery { mockGetDogUseCase() } returns (expectedError)

        //when
        val homeViewModel = DogListViewModel(mockGetDogUseCase)

        //then
        coVerify(exactly = 1) { mockGetDogUseCase() }
        assertThat(homeViewModel.viewState.getOrAwaitValue()).isEqualTo(expectedError)
    }

    @Test
    fun `Given vm initialised When Retry ViewEvent occurred Then ViewState should get expectedResult`() {
        //Given
        val expectedResult = Resource.Success(fixture<List<Dog>>())
        coEvery { mockGetDogUseCase() } returns (expectedResult)
        val homeViewModel = DogListViewModel(mockGetDogUseCase)

        //when
        homeViewModel.onViewEvent(DogListViewEvents.Refresh)

        //then
        coVerify(exactly = 2) { mockGetDogUseCase() }
        assertThat(homeViewModel.viewState.getOrAwaitValue()).isEqualTo(expectedResult)
    }

    @Test
    fun `Given vm initialised When TryAgain ViewEvent occurred Then ViewState should get expectedResult`() {
        //Given
        val expectedResult = Resource.Success(fixture<List<Dog>>())
        coEvery { mockGetDogUseCase() } returns (expectedResult)

        //when
        val homeViewModel = DogListViewModel(mockGetDogUseCase)
        homeViewModel.onViewEvent(DogListViewEvents.TryAgain)

        //then
        coVerify(exactly = 2) { mockGetDogUseCase() }
        assertThat(homeViewModel.viewState.getOrAwaitValue()).isEqualTo(expectedResult)
    }

    @Test
    fun `Given vm initialised When OpenDogPhotosScreen ViewEvent OpenDogPhotosScreen Then ViewState should NavToDogPhotos`() {
        //Given
        val dog = fixture<Dog>()
        val result = Resource.Success(listOf(dog))
        coEvery { mockGetDogUseCase() } returns (result)
        val homeViewModel = DogListViewModel(mockGetDogUseCase)

        //when
        homeViewModel.onViewEvent(DogListViewEvents.OpenDogPhotosScreen(dog))

        //then
        coVerify(exactly = 1) { mockGetDogUseCase() }
        assertThat(homeViewModel.singleViewState.getOrAwaitValue()).isEqualTo(
            DogListSingleViewState.NavToDogPhotos(dog)
        )
    }
}