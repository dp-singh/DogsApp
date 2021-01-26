package com.dog.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appmattus.kotlinfixture.kotlinFixture
import com.dog.data.model.local.DogEntity
import com.dog.data.model.remote.DogsResponseDto
import com.dog.data.repository.DogsRepository
import com.dog.domain.mapper.dog.DogsFromDataToDbMapper
import com.dog.domain.mapper.dog.DogsFromDbToDataMapper
import com.dog.domain.mapper.dog.DogsFromNetworkToDataMapper
import com.dog.domain.model.Resource
import com.dog.domain.utils.AppDispatchers
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetDogListUseCaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val mockDogsRepository: DogsRepository = mockk()
    private val testDispatcher: AppDispatchers = AppDispatchers(
        ioDispatcher = Dispatchers.Unconfined,
        defaultDispatcher = Dispatchers.Unconfined,
        mainDispatcher = Dispatchers.Unconfined
    )
    private val networkToDataMapper = DogsFromNetworkToDataMapper()
    private val dbToDataMapper = DogsFromDbToDataMapper()
    private val dataToDbMapper = DogsFromDataToDbMapper()
    private lateinit var getDogListUseCase: GetDogListUseCase
    private val fixture = kotlinFixture()

    @Before
    fun setUp() {
        getDogListUseCase = GetDogListUseCase(
            dogsRepository = mockDogsRepository,
            networkToDataMapper = networkToDataMapper,
            dbToDataMapper = dbToDataMapper,
            dataToDbMapper = dataToDbMapper,
            dispatcher = testDispatcher
        )
    }

    @After
    fun clear() {
        clearMocks(mockDogsRepository)
    }

    @Test
    fun `Given DogsRepository return success  When getDogListUseCase is called Then expect list of dogs`() =
        runBlockingTest {
            //given
            val apiResponse: DogsResponseDto = fixture()
            coEvery { mockDogsRepository.getDogListFromNetwork() }.returns(apiResponse)
            coEvery { mockDogsRepository.persistIntoDb(any()) }.returns(Unit)

            //when
            val result = getDogListUseCase()

            //then
            coVerifyAll {
                mockDogsRepository.persistIntoDb(any())
                mockDogsRepository.getDogListFromNetwork()
            }
            assertThat(result).isEqualTo(Resource.Success(networkToDataMapper.map(apiResponse)))
        }

    @Test
    fun `Given DogsRepository return error from DB & Network  When getDogListUseCase is called Then expect an error`() =
        runBlockingTest {
            //given
            val expectedError = IllegalArgumentException()
            coEvery { mockDogsRepository.getDogListFromNetwork() }.throws(expectedError)
            coEvery { mockDogsRepository.getDogTypeFromDb() }.throws(expectedError)

            //when
            val result = getDogListUseCase()

            //then
            coVerifyAll {
                mockDogsRepository.getDogListFromNetwork()
                mockDogsRepository.getDogTypeFromDb()
            }
            assertThat(result).isEqualTo(Resource.Error(expectedError))
        }

    @Test
    fun `Given DogsRepository Network error & DB success  When getDogListUseCase is called Then expect success with network error`() =
        runBlockingTest {
            //given
            val expectedResponse = fixture<List<DogEntity>>()
            val expectedError = IllegalArgumentException()
            coEvery { mockDogsRepository.getDogListFromNetwork() }.throws(expectedError)
            coEvery { mockDogsRepository.getDogTypeFromDb() }.returns(expectedResponse)

            //when
            val result = getDogListUseCase()

            //then
            coVerifyAll {
                mockDogsRepository.getDogListFromNetwork()
                mockDogsRepository.getDogTypeFromDb()
            }
            assertThat(result).isEqualTo(
                Resource.SuccessFromCache(
                    data = dbToDataMapper.map(expectedResponse),
                    throwable = expectedError
                )
            )
        }
}