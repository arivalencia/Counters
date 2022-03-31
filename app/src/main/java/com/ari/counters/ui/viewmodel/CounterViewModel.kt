package com.ari.counters.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ari.counters.domain.model.CounterDomain
import com.ari.counters.domain.model.Result
import com.ari.counters.domain.usecases.AddCounterUseCase
import com.ari.counters.domain.usecases.GetAllCountersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val getAllCountersUseCase: GetAllCountersUseCase,
    private val addCounterUseCase: AddCounterUseCase
): ViewModel() {

    private val counterList: MutableLiveData<List<CounterDomain>> = MutableLiveData(arrayListOf())
    private val _countersToShow: MutableLiveData<List<CounterDomain>> = MutableLiveData(arrayListOf())
    val countersToShow: LiveData<List<CounterDomain>> get() = _countersToShow

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onErrorRequest: MutableLiveData<String> = MutableLiveData()
    val onErrorRequest: LiveData<String> = _onErrorRequest

    fun getAllCounters() = viewModelScope.launch {
        _isLoading.postValue(true)
        when (val result = getAllCountersUseCase()) {
            is Result.Error -> _onErrorRequest.postValue(result.error)
            is Result.Success -> {
                counterList.postValue(result.result)
                _countersToShow.postValue(result.result)
            }
        }
        _isLoading.postValue(false)
    }

    fun addCounter(counterTitle: String) = viewModelScope.launch {
        _isLoading.postValue(true)

        when(val result = addCounterUseCase(counterTitle)) {
            is Result.Error -> _onErrorRequest.postValue(result.error)
            is Result.Success -> {
                val originalList = ArrayList(counterList.value!!)
                originalList.add(result.result)
                counterList.postValue(originalList)

                val toShowList = ArrayList(countersToShow.value!!)
                toShowList.add(result.result)
                _countersToShow.postValue(toShowList)
            }
        }

        _isLoading.postValue(false)

    }


}