package com.ari.counters.domain.usecases

import com.ari.counters.data.model.Response
import com.ari.counters.data.repository.CounterRepository
import com.ari.counters.domain.model.CounterDomain
import com.ari.counters.domain.model.Result
import com.ari.counters.domain.model.toDomain
import javax.inject.Inject

class IncrementCounterUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke(counterId: String): Result<CounterDomain> =
        when (val response = counterRepository.incrementCounter(counterId)) {
            is Response.Error -> Result.Error(response.error)
            is Response.Success -> {
                // Update local data
                counterRepository.clearLocalTable()
                counterRepository.insertCounterListInLocal(response.result)

                Result.Success(
                    response.result.find { counter -> counter.id == counterId }!!.toDomain()
                )
            }
        }

}