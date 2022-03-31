package com.ari.counters.domain.usecases

import com.ari.counters.data.model.CounterData
import com.ari.counters.data.model.Response
import com.ari.counters.data.repository.CounterRepository
import com.ari.counters.domain.model.CounterDomain
import com.ari.counters.domain.model.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class IncrementCounterUseCaseTest {

    private lateinit var incrementCounterUseCase: IncrementCounterUseCase

    @RelaxedMockK
    private lateinit var counterRepository: CounterRepository

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        incrementCounterUseCase = IncrementCounterUseCase(counterRepository)
    }

    @Test
    fun `when the api return an error then get an error`() = runBlocking {
        // Given
        coEvery { counterRepository.incrementCounter(any()) } returns Response.Error("This is an error")

        // When
        val result = incrementCounterUseCase("")

        //Then
        coVerify(exactly = 0) { counterRepository.clearLocalTable() }
        coVerify(exactly = 0) { counterRepository.insertCounterListInLocal(any()) }
        assert(result is Result.Error)
    }

    @Test
    fun `when the api return a list then get and save data in local data base`() = runBlocking {
        // Given
        val list = listOf(CounterData(id = "dkcjbsd", title = "title", count = 3))
        coEvery { counterRepository.incrementCounter(any()) } returns Response.Success(list)

        // When
        val result: Result<CounterDomain> = incrementCounterUseCase("dkcjbsd")

        //Then
        coVerify(exactly = 1) { counterRepository.clearLocalTable() }
        coVerify(exactly = 1) { counterRepository.insertCounterListInLocal(any()) }
        assert(result is Result.Success)
    }

}