package com.dog.app.features.dog.photos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appmattus.kotlinfixture.kotlinFixture
import com.dog.app.features.getOrAwaitValue
import com.dog.domain.model.Resource
import com.dog.domain.usecase.GetDogPhotosUseCase
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import java.util.concurrent.CancellationException

class DogPhotosViewModelTest : AutoCloseKoinTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val mockGetDogPhotosUseCase: GetDogPhotosUseCase = mockk()

    private val fixture = kotlinFixture()

    @Test
    fun `Given vm initialised When dog photos return success Then ViewState should be success`() {
        //Given
        val expectedResult = Resource.Success(fixture<List<String>>())
        coEvery { mockGetDogPhotosUseCase(any()) } returns (expectedResult)

        //when
        val homeViewModel = DogPhotosViewModel(mockGetDogPhotosUseCase, fixture())

        //then
        coVerify(exactly = 1) { mockGetDogPhotosUseCase(any()) }

        assertThat(homeViewModel.viewState.getOrAwaitValue()).isEqualTo(expectedResult)
    }

    @Test
    fun `Given vm initialised When dog photos return error Then ViewState should be error`() {
        //Given
        val expectedResult = Resource.Error(CancellationException())
        coEvery { mockGetDogPhotosUseCase(any()) } returns (expectedResult)

        //when
        val homeViewModel = DogPhotosViewModel(mockGetDogPhotosUseCase, fixture())

        //then
        coVerify(exactly = 1) { mockGetDogPhotosUseCase(any()) }

        assertThat(homeViewModel.viewState.getOrAwaitValue()).isEqualTo(expectedResult)
    }

    @Test
    fun `Given vm initialised When Retry ViewEvent occurred Then ViewState should get expectedResult`() {
        //Given
        val expectedResult = Resource.Success(listOf("urls"))
        coEvery { mockGetDogPhotosUseCase(any()) } returns (expectedResult)
        val homeViewModel = DogPhotosViewModel(mockGetDogPhotosUseCase, fixture())

        //when
        homeViewModel.onViewEvent(DogPhotosViewEvent.Refresh)

        //then
        coVerify(exactly = 2) { mockGetDogPhotosUseCase(any()) }
        assertThat(homeViewModel.viewState.getOrAwaitValue()).isEqualTo(expectedResult)
    }

    @Test
    fun `Given vm initialised When TryAgain ViewEvent occurred Then ViewState should get expectedResult`() {
        //Given
        val expectedResult = Resource.Success(listOf("urls"))
        coEvery { mockGetDogPhotosUseCase(any()) } returns (expectedResult)
        val homeViewModel = DogPhotosViewModel(mockGetDogPhotosUseCase, fixture())

        //when
        homeViewModel.onViewEvent(DogPhotosViewEvent.TryAgain)

        //then
        coVerify(exactly = 2) { mockGetDogPhotosUseCase(any()) }
        assertThat(homeViewModel.viewState.getOrAwaitValue()).isEqualTo(expectedResult)
    }

}