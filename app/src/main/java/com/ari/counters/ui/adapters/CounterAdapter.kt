package com.ari.counters.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ari.counters.databinding.ItemCounterBinding
import com.ari.counters.domain.model.CounterDomain

class CounterAdapter(
    //private val counters: List<CounterDomain>,
    private val events: CounterListener
) : RecyclerView.Adapter<CounterAdapter.ViewHolder>() {

    private val counters = arrayListOf<CounterDomain>()

    fun setList(newList: List<CounterDomain>) {
        counters.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

    fun updateCounter(newCount: Int, position: Int) {
        counters[position].count = newCount
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemCounterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(counters[position], position)

    override fun getItemCount(): Int = counters.size

    inner class ViewHolder(
        private val binding: ItemCounterBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(counter: CounterDomain, position: Int) {
            binding.counter = counter

            // On click increment drawable
            binding.ivIncrement.setOnClickListener { events.onIncrementCounter(counter, position) }

            // On click decrement drawable
            binding.ivDecrement.setOnClickListener { events.onDecrementCounter(counter, position) }

            // On click delete drawable
            binding.ivDelete.setOnClickListener { events.onDeleteCounter(counter, position) }
        }

    }

    public interface CounterListener {
        fun onIncrementCounter(counter: CounterDomain, position: Int)
        fun onDecrementCounter(counter: CounterDomain, position: Int)
        fun onDeleteCounter(counter: CounterDomain, position: Int)
    }

}