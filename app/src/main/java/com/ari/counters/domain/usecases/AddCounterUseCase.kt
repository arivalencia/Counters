package com.ari.counters.domain.usecases

import com.ari.counters.data.model.Response
import com.ari.counters.data.repository.CounterRepository
import com.ari.counters.domain.model.CounterDomain
import com.ari.counters.domain.model.Result
import com.ari.counters.domain.model.toDomain
import javax.inject.Inject

class AddCounterUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke(counterTitle: String): Result<CounterDomain> =
        when (val response = counterRepository.addCounter(counterTitle)) {
            is Response.Error -> Result.Error(response.error)
            is Response.Success -> Result.Success(response.result.toDomain())
        }

}