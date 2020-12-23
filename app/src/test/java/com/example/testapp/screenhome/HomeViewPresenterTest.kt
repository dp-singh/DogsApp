package com.example.testapp.screenhome

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.domain.model.Resource
import com.example.domain.model.TopEmployeeWithSalary200
import com.example.domain.usecase.GetTopPaidEmployeeUseCase
import com.example.testapp.util.TestLifecycleOwner
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import kotlin.coroutines.cancellation.CancellationException

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class HomeViewPresenterTest : AutoCloseKoinTest() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockGetTopPaidEmployeeUseCase: GetTopPaidEmployeeUseCase = mockk()
    private val testLifeCycleOwner = TestLifecycleOwner()

    @Test
    fun `Given useCase return success Then ViewState should return Success`() {
        //Given
        val success = Resource.Success(
            TopEmployeeWithSalary200(
                topEmployee = "Random",
                restEmployeeCount = 10
            )
        )
        coEvery { mockGetTopPaidEmployeeUseCase() } returns (success)
        //when
        val homeViewModel =
            HomeViewPresenter(mockGetTopPaidEmployeeUseCase, testLifeCycleOwner.lifecycle)
        testLifeCycleOwner.onStart()

        //then
        coVerify(atMost = 1) { mockGetTopPaidEmployeeUseCase() }
        assertThat(homeViewModel.viewState.value).isEqualTo(success)
    }

    @Test
    fun `Given useCase return exception Then the ViewState should have Error`() {
        //Given
        val error = Resource.Error(CancellationException())
        coEvery { mockGetTopPaidEmployeeUseCase() } returns (error)

        //when
        val homeViewModel =
            HomeViewPresenter(mockGetTopPaidEmployeeUseCase, testLifeCycleOwner.lifecycle)
        testLifeCycleOwner.onStart()

        //then
        coVerify(atMost = 1) { mockGetTopPaidEmployeeUseCase() }
        assertThat(homeViewModel.viewState.value).isEqualTo(error)
    }

    @Test
    fun `Given useCase return success When retry event occurred Then ViewState should have success`() {
        //Given
        val success = Resource.Success(
            TopEmployeeWithSalary200(
                topEmployee = "Random",
                restEmployeeCount = 10
            )
        )
        coEvery { mockGetTopPaidEmployeeUseCase() } returns (success)

        //When
        val homeViewModel =
            HomeViewPresenter(mockGetTopPaidEmployeeUseCase, testLifeCycleOwner.lifecycle)
        testLifeCycleOwner.onStart()
        homeViewModel.onViewEvent(HomeViewEvents.Refresh)

        //Then
        coVerify(atMost = 2) { mockGetTopPaidEmployeeUseCase() }
        assertThat(homeViewModel.viewState.value).isEqualTo(success)
    }
}
