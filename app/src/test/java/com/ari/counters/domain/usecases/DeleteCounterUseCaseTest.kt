package com.ari.counters.domain.usecases

import com.ari.counters.data.model.CounterData
import com.ari.counters.data.model.Response
import com.ari.counters.data.repository.CounterRepository
import com.ari.counters.domain.model.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DeleteCounterUseCaseTest {

    private lateinit var deleteCounterUseCase: DeleteCounterUseCase

    @RelaxedMockK
    private lateinit var counterRepository: CounterRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        deleteCounterUseCase = DeleteCounterUseCase(counterRepository)
    }

    @Test
    fun `when the api return an error then get an error`() = runBlocking {
        // Given
        coEvery { counterRepository.deleteCounter(any()) } returns Response.Error("This is an error")

        // When
        val result = deleteCounterUseCase("any")

        //Then
        coVerify(exactly = 0) { counterRepository.clearLocalTable() }
        coVerify(exactly = 0) { counterRepository.insertCounterListInLocal(any()) }
        assert(result is Result.Error)
    }

    @Test
    fun `when the api return a list then get and save data in local data base`() = runBlocking {
        // Given
        val list = listOf(CounterData(id = "dkcjbsd", title = "title", count = 3))
        coEvery { counterRepository.deleteCounter(any()) } returns Response.Success(list)

        // When
        val result = deleteCounterUseCase("any")

        //Then
        coVerify(exactly = 1) { counterRepository.clearLocalTable() }
        coVerify(exactly = 1) { counterRepository.insertCounterListInLocal(any()) }
        assert(result is Result.Success)
    }

}