package com.dog.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.appmattus.kotlinfixture.kotlinFixture
import com.dog.data.model.local.DogPhotosEntity
import com.dog.data.model.remote.DogPhotosResponseDto
import com.dog.data.repository.DogPhotosRepository
import com.dog.domain.model.Dog
import com.dog.domain.model.Resource
import com.dog.domain.utils.AppDispatchers
import com.google.common.truth.Truth.assertThat
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerifyAll
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.IllegalStateException

@ExperimentalCoroutinesApi
class GetDogPhotosUseCaseTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val mockDogPhotosRepository: DogPhotosRepository = mockk()
    private val testDispatcher: AppDispatchers = AppDispatchers(
        ioDispatcher = Dispatchers.Unconfined,
        defaultDispatcher = Dispatchers.Unconfined,
        mainDispatcher = Dispatchers.Unconfined
    )
    private val fixture = kotlinFixture()
    private lateinit var getDogPhotosUseCase: GetDogPhotosUseCase

    @Before
    fun setUp() {
        getDogPhotosUseCase = GetDogPhotosUseCase(
            dogPhotosRepository = mockDogPhotosRepository,
            dispatcher = testDispatcher
        )
    }

    @After
    fun clear() {
        clearMocks(mockDogPhotosRepository)
    }

    @Test
    fun `Given DogsPhotoRepository with breed return success  When getDogPhotosUseCase is called Then expect list of dog photos`() =
        runBlockingTest {
            //given
            val expectedPhotosList = fixture<DogPhotosResponseDto>()
            val dogWithBreed = Dog(breed = fixture())

            coEvery { mockDogPhotosRepository.getByBreed(dogWithBreed.breed) }.returns(
                expectedPhotosList
            )
            coEvery { mockDogPhotosRepository.storeItIntoDb(any()) }.returns(Unit)

            //when
            val result = getDogPhotosUseCase(dogWithBreed)

            //then
            coVerifyAll {
                mockDogPhotosRepository.getByBreed(dogWithBreed.breed)
                mockDogPhotosRepository.storeItIntoDb(any())
            }
            assertThat(result)
                .isEqualTo(Resource.Success(expectedPhotosList.photoList))
        }

    @Test
    fun `Given DogsPhotoRepository with subBreed return success  When getDogPhotosUseCase is called Then expect list of dog photos`() =
        runBlockingTest {

            //given
            val expectedPhotosList = fixture<DogPhotosResponseDto>()
            val dogWithBreed = Dog(breed = fixture(), subBreed = fixture<String>())

            coEvery {
                mockDogPhotosRepository.getByBreedAndSubBreed(
                    dogWithBreed.breed,
                    dogWithBreed.subBreed.orEmpty()
                )
            }.returns(expectedPhotosList)
            coEvery { mockDogPhotosRepository.storeItIntoDb(any()) }.returns(Unit)

            //when
            val result = getDogPhotosUseCase(dogWithBreed)

            //then
            coVerifyAll {
                mockDogPhotosRepository.getByBreedAndSubBreed(
                    dogWithBreed.breed,
                    dogWithBreed.subBreed.orEmpty()
                )
                mockDogPhotosRepository.storeItIntoDb(any())
            }
            assertThat(result)
                .isEqualTo(Resource.Success(expectedPhotosList.photoList))
        }

    @Test
    fun `Given DogsPhotoRepository return error from DB & Network  When getDogPhotosUseCase is called Then expect an error`() =
        runBlockingTest {

            //given
            val dogWithBreed = Dog(breed = fixture())

            val expectedError = IllegalStateException()
            coEvery {
                mockDogPhotosRepository.getByBreed(any())
            }.throws(expectedError)

            coEvery {
                mockDogPhotosRepository.getDogPhotosFromDb(any(), any())
            }.throws(expectedError)

            //when
            val result = getDogPhotosUseCase(dogWithBreed)

            //then
            coVerifyAll {
                mockDogPhotosRepository.getByBreed(any())
                mockDogPhotosRepository.getDogPhotosFromDb(any(), any())
            }
            assertThat(result)
                .isEqualTo(Resource.Error(expectedError))
        }

    @Test
    fun `Given DogsPhotoRepository Network error & DB success  When getDogPhotosUseCase is called Then expect success with network error`() =
        runBlockingTest {
            //given
            val expectedPhotosList = fixture<List<DogPhotosEntity>>()
            val expectedError = IllegalStateException()
            val dogWithBreed = Dog(breed = fixture(), subBreed = fixture<String>())

            coEvery {
                mockDogPhotosRepository.getByBreedAndSubBreed(any(), any())
            }.throws(expectedError)
            coEvery { mockDogPhotosRepository.getDogPhotosFromDb(any(), any()) }.returns(
                expectedPhotosList
            )

            //when
            val result = getDogPhotosUseCase(dogWithBreed)

            //then
            coVerifyAll {
                mockDogPhotosRepository.getByBreedAndSubBreed(any(), any())
                mockDogPhotosRepository.getDogPhotosFromDb(any(), any())
            }
            assertThat(result)
                .isEqualTo(
                    Resource.SuccessFromCache(
                        data = expectedPhotosList.map { it.imageUrl },
                        throwable = expectedError
                    )
                )
        }

}