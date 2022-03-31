package com.ari.counters.domain.usecases

import com.ari.counters.data.model.Response
import com.ari.counters.data.repository.CounterRepository
import com.ari.counters.domain.model.CounterDomain
import com.ari.counters.domain.model.Result
import com.ari.counters.domain.model.toDomain
import javax.inject.Inject

class GetAllCountersUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke(): Result<List<CounterDomain>> =
        when (val response = counterRepository.getAllCounter()) {
            is Response.Error -> {
                //Result.Error(response.error)
                Result.Success(counterRepository.getAllLocalCounters().map { it.toDomain() })
            }
            is Response.Success -> {
                // Update local data
                counterRepository.clearLocalTable()
                counterRepository.insertCounterListInLocal(response.result)

                Result.Success(response.result.map { it.toDomain() })
            }
        }

}