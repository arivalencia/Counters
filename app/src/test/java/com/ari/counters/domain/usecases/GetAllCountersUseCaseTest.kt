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

class GetAllCountersUseCaseTest {

    private lateinit var getAllCountersUseCase: GetAllCountersUseCase

    @RelaxedMockK
    private lateinit var counterRepository: CounterRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getAllCountersUseCase = GetAllCountersUseCase(counterRepository)
    }

    @Test
    fun `when the api return an error then get counters of local data base`() = runBlocking {
        // Given
        coEvery { counterRepository.getAllCounter() } returns Response.Error<List<CounterData>>("This is an error")

        // When
        val result = getAllCountersUseCase()

        //Then
        coVerify(exactly = 1) { counterRepository.getAllLocalCounters() }
        coVerify(exactly = 0) { counterRepository.clearLocalTable() }
        coVerify(exactly = 0) { counterRepository.insertCounterListInLocal(any()) }
        assert(result is Result.Success)
    }

    @Test
    fun `when the api return a list then get and save data in local data base`() = runBlocking {
        // Given
        val list = listOf(
            CounterData(
                id = "aasdadd",
                title = "title",
                count = 1
            ))
        coEvery { counterRepository.getAllCounter() } returns Response.Success(list)

        // When
        val result = getAllCountersUseCase()

        //Then
        coVerify(exactly = 0) { counterRepository.getAllLocalCounters() }
        coVerify(exactly = 1) { counterRepository.clearLocalTable() }
        coVerify(exactly = 1) { counterRepository.insertCounterListInLocal(any()) }
        assert(result is Result.Success)
    }

}