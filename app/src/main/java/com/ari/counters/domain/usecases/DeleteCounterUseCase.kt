package com.ari.counters.domain.usecases

import com.ari.counters.data.model.Response
import com.ari.counters.data.repository.CounterRepository
import com.ari.counters.domain.model.Result
import javax.inject.Inject

class DeleteCounterUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke(counterId: String): Result<Any> =
        when(val response = counterRepository.deleteCounter(counterId)) {
            is Response.Error -> Result.Error(response.error)
            is Response.Success -> Result.Success(response.result)
        }

}