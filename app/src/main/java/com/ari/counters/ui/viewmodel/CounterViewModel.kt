package com.ari.counters.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ari.counters.domain.model.CounterDomain
import com.ari.counters.domain.model.Result
import com.ari.counters.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.util.zip.ZipEntry
import javax.inject.Inject

@HiltViewModel
class CounterViewModel @Inject constructor(
    private val getAllCountersUseCase: GetAllCountersUseCase,
    private val addCounterUseCase: AddCounterUseCase,
    private val incrementCounterUseCase: IncrementCounterUseCase,
    private val decrementCounterUseCase: DecrementCounterUseCase,
    private val deleteCounterUseCase: DeleteCounterUseCase
) : ViewModel() {

    private val _counterList: MutableLiveData<List<CounterDomain>> = MutableLiveData(arrayListOf())
    private val _countersToShow: MutableLiveData<List<CounterDomain>> =
        MutableLiveData(arrayListOf())
    val countersToShow: LiveData<List<CounterDomain>> get() = _countersToShow

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _onErrorRequest: MutableLiveData<String> = MutableLiveData()
    val onErrorRequest: LiveData<String> = _onErrorRequest

    private val _inputSearch = MutableLiveData("")

    fun getAllCounters() = viewModelScope.launch {
        _isLoading.postValue(true)
        when (val result = getAllCountersUseCase()) {
            is Result.Error -> _onErrorRequest.postValue(result.error)
            is Result.Success -> {
                _counterList.postValue(result.result)
                _countersToShow.postValue(result.result)
                //onSearchCounter(_inputSearch.value!!)
            }
        }
        _isLoading.postValue(false)
    }

    fun addCounter(counterTitle: String) = viewModelScope.launch {
        _isLoading.postValue(true)
        when (val result = addCounterUseCase(counterTitle.trim())) {
            is Result.Error -> _onErrorRequest.postValue(result.error)
            is Result.Success -> {
                val originalList = ArrayList(_counterList.value!!)
                originalList.add(result.result)
                _counterList.postValue(originalList)

                val toShowList = ArrayList(countersToShow.value!!)
                toShowList.add(result.result)
                _countersToShow.postValue(toShowList)
            }
        }
        _isLoading.postValue(false)

    }

    fun deleteCounter(counterId: String) = viewModelScope.async{
        _isLoading.postValue(true)

        when (val result = deleteCounterUseCase(counterId)) {
            is Result.Error -> _onErrorRequest.postValue(result.error)
            is Result.Success -> {
                //synchronized(this@CounterViewModel) {
                    val originalList = ArrayList(_counterList.value!!)
                    originalList.find { it.id == counterId }?.let { counterDeleted ->
                        originalList.remove(counterDeleted)
                        _counterList.postValue(originalList)
                    }

                    val toShowList = ArrayList(countersToShow.value!!)
                    toShowList.first { it.id == counterId }?.let { counterDeleted ->
                        toShowList.remove(counterDeleted)
                        _countersToShow.postValue(toShowList)
                    }
                //}
            }
        }

        _isLoading.postValue(false)
    }

    fun incrementCounter(counterId: String) = viewModelScope.launch {
        _isLoading.postValue(true)

        when (val result = incrementCounterUseCase(counterId)) {
            is Result.Error -> _onErrorRequest.postValue(result.error)
            is Result.Success -> updateListsOnIncrementOrDecrementCounter(result.result)
        }

        _isLoading.postValue(false)
    }

    fun decrementCounter(counterId: String) = viewModelScope.launch {
        _isLoading.postValue(true)
        when (val result = decrementCounterUseCase(counterId)) {
            is Result.Error -> _onErrorRequest.postValue(result.error)
            is Result.Success -> updateListsOnIncrementOrDecrementCounter(result.result)
        }
        _isLoading.postValue(false)
    }

    private fun updateListsOnIncrementOrDecrementCounter(counterUpdated: CounterDomain) {
        val originalList = ArrayList(_counterList.value!!)
        val position: Int =
            originalList.indexOf(originalList.find { counter -> counter.id == counterUpdated.id })
        if (position >= 0) { // if counter exist in list -> update
            originalList[position] = counterUpdated
            _counterList.postValue(originalList)
        }

        val toShowList = ArrayList(countersToShow.value!!)
        val positionOfListToShow: Int =
            toShowList.indexOf(toShowList.find { counter -> counter.id == counterUpdated.id })
        if (positionOfListToShow >= 0) { // if counter exist in list -> update
            toShowList[position] = counterUpdated
            _countersToShow.postValue(toShowList)
        }
    }

    fun onSearchCounter(inputSearch: String) = viewModelScope.launch {
        if (inputSearch.isEmpty() || inputSearch.isBlank()) { // Invalid search
            _countersToShow.postValue(_counterList.value!!) // Show all
            return@launch
        }

        val coincidences: List<CounterDomain> = _counterList.value!!.filter { counter ->
            counter.title.lowercase().contains(inputSearch.lowercase())
        }
        _countersToShow.postValue(coincidences) // Show coincidences

        _inputSearch.postValue(inputSearch)
    }

}