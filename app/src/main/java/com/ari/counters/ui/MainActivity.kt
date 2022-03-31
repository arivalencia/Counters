package com.ari.counters.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.ari.counters.R
import com.ari.counters.databinding.ActivityMainBinding
import com.ari.counters.domain.model.CounterDomain
import com.ari.counters.ui.viewmodel.CounterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private lateinit var countersAdapter: CounterAdapter
    private val counterViewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initComponents()
        initObservers()

        counterViewModel.getAllCounters()

    }

    private fun initComponents() {
        countersAdapter = CounterAdapter(
            //counterViewModel.countersToShow.value!!,
            object : CounterAdapter.CounterListener {
                override fun openCounter(counter: CounterDomain, position: Int) {
                    TODO("Not yet implemented")
                }

                override fun onDelete(counter: CounterDomain, position: Int) {
                    TODO("Not yet implemented")
                }
            })
        binding.rvCounters.adapter = countersAdapter
    }

    private fun initObservers() {
        counterViewModel.isLoading.observe(this) { isLoading ->
            binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        counterViewModel.onErrorRequest.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }

        counterViewModel.countersToShow.observe(this) { counters ->
            countersAdapter.setList(counters)

            var totalCounter = 0
            counters.forEach { counter -> totalCounter +=  counter.count }
            binding.tvTotalCounter.text = "${getString(R.string.total_counter)} $totalCounter"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
