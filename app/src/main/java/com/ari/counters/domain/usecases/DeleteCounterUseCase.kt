package com.ari.counters.domain.usecases

import com.ari.counters.data.model.CounterData
import com.ari.counters.data.model.Response
import com.ari.counters.data.repository.CounterRepository
import com.ari.counters.domain.model.Result
import javax.inject.Inject

class DeleteCounterUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {

    suspend operator fun invoke(counterId: String): Result<List<CounterData>> =
        when(val response = counterRepository.deleteCounter(counterId)) {
            is Response.Error -> Result.Error(response.error)
            is Response.Success -> {
                // Update local data
                counterRepository.clearLocalTable()
                counterRepository.insertCounterListInLocal(response.result)

                Result.Success(response.result)
            }
        }

}