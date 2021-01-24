package com.example.testapp.features

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.model.Resource
import com.example.testapp.features.breedlist.HomeViewEvents
import com.example.testapp.features.breedlist.HomeViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import kotlin.coroutines.cancellation.CancellationException

@RunWith(AndroidJUnit4::class)
class HomeViewModelTest : AutoCloseKoinTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val mockGetTopPaidEmployeeUseCase: GetTopPaidEmployeeUseCase = mockk()

    @Test
    fun `Given success returned from the useCase When view model gets created Then ViewState should be Success`() {
        //Given
        val success = Resource.Success(
            TopEmployeeWithSalary200(
                topEmployee = "Random",
                restEmployeeCount = 10
            )
        )
        coEvery { mockGetTopPaidEmployeeUseCase() } returns (success)

        //when
        val homeViewModel = HomeViewModel(mockGetTopPaidEmployeeUseCase)

        //then
        coVerify(atMost = 1) { mockGetTopPaidEmployeeUseCase() }
        homeViewModel.viewState.getOrAwaitValue().apply {
            assertThat(this).isEqualTo(success)
        }
    }

    @Test
    fun `Given exception returned from useCase When view model gets created Then ViewState should be Error`() {
        //Given
        val error = Resource.Error(CancellationException())
        coEvery { mockGetTopPaidEmployeeUseCase() } returns (error)

        //when
        val homeViewModel = HomeViewModel(mockGetTopPaidEmployeeUseCase)

        //then
        coVerify(atMost = 1) { mockGetTopPaidEmployeeUseCase() }
        homeViewModel.viewState.getOrAwaitValue().apply {
            assertThat(this).isEqualTo(error)
        }
    }

    @Test
    fun `Given success returned from useCase When retry event occurred Then ViewState should be success`() {
        //Given
        val error = Resource.Error(CancellationException())
        coEvery { mockGetTopPaidEmployeeUseCase() } returns (error)

        //when
        val homeViewModel = HomeViewModel(mockGetTopPaidEmployeeUseCase)
        homeViewModel.onViewEvent(HomeViewEvents.Refresh)
        //then
        coVerify(atMost = 2) { mockGetTopPaidEmployeeUseCase() }
        homeViewModel.viewState.getOrAwaitValue().apply {
            assertThat(this).isEqualTo(error)
        }
    }
}
