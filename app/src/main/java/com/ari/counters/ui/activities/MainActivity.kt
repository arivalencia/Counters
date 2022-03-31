package com.ari.counters.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.ari.counters.R
import com.ari.counters.databinding.ActivityMainBinding
import com.ari.counters.domain.model.CounterDomain
import com.ari.counters.ui.adapters.CounterAdapter
import com.ari.counters.ui.dialogs.AddCounterBottomSheet
import com.ari.counters.ui.dialogs.ErrorBottomSheet
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

        setOnClickListeners()
        initComponents()
        initObservers()

        counterViewModel.getAllCounters()

    }

    private fun setOnClickListeners() {
        binding.tvAddCounter.setOnClickListener { onCreateCounter() }
    }

    private fun onCreateCounter() {
        val dialog = AddCounterBottomSheet()
        dialog.setListener(object : AddCounterBottomSheet.AddCounterListener {
            override fun onCreateCounter(counterTitle: String) {
                counterViewModel.addCounter(counterTitle)
            }
        })
        dialog.show(supportFragmentManager, dialog.tag)
    }

    private fun initComponents() {
        countersAdapter = CounterAdapter(object : CounterAdapter.CounterListener {
            override fun onIncrementCounter(counter: CounterDomain, position: Int) {
                counterViewModel.incrementCounter(counter.id)
            }

            override fun onDecrementCounter(counter: CounterDomain, position: Int) {
                counterViewModel.decrementCounter(counter.id)
            }

            override fun onDeleteCounter(counter: CounterDomain, position: Int) {
                counterViewModel.deleteCounter(counter.id)
            }
        })
        binding.rvCounters.adapter = countersAdapter
    }

    private fun initObservers() {
        // On Loading
        counterViewModel.isLoading.observe(this) { isLoading ->
            binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // On Error Request
        counterViewModel.onErrorRequest.observe(this) { error -> onErrorRequest(error) }

        // Refresh adapter when [countersToShow] change
        counterViewModel.countersToShow.observe(this) { counters ->
            countersAdapter.setList(counters.reversed())
            binding.countersEmptyNotice.visibility =
                if (counters.isEmpty()) View.VISIBLE else View.GONE

            var totalCounter = 0
            counters.forEach { counter -> totalCounter += counter.count }
            binding.tvTotalCounter.text = "${getString(R.string.total_counter)} $totalCounter"
        }
    }

    // If request is bad response show dialog error
    private fun onErrorRequest(error: String) {
        val dialog = ErrorBottomSheet()
        dialog.show(supportFragmentManager, dialog.tag)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
