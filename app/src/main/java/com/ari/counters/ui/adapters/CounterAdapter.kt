package com.ari.counters.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ari.counters.R
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

    fun removeItem(counter: CounterDomain, position: Int){
        counters.remove(counter)
        notifyItemRemoved(position)
    }

    fun restoreItem(counter: CounterDomain, position: Int) {
        counters.add(position, counter)
        notifyItemInserted(position)
    }

    fun getSelections(): List<CounterDomain> = counters.filter { it.isSelected }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemCounterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(counters[position], position)

    override fun getItemCount(): Int = counters.size

    inner class ViewHolder(
        private val binding: ItemCounterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(counter: CounterDomain, position: Int) {
            binding.counter = counter

            binding.root.setOnClickListener {
                counter.isSelected = !counter.isSelected
                binding.container.setBackgroundResource(if (counter.isSelected) R.color.counter_selected else R.color.white)
                events.onClickAllCounter(counter, position)
            }

            // On click increment drawable
            binding.ivIncrement.setOnClickListener { events.onIncrementCounter(counter, position) }

            // On click decrement drawable
            binding.ivDecrement.setOnClickListener { events.onDecrementCounter(counter, position) }

            // On click decrement drawable
            binding.ivShare.setOnClickListener { events.onShareCounter(counter, position) }

            // On click delete drawable
            binding.ivDelete.setOnClickListener { events.onDeleteCounter(counter, position) }
        }

    }

    interface CounterListener {
        fun onClickAllCounter(counter: CounterDomain, position: Int)
        fun onIncrementCounter(counter: CounterDomain, position: Int)
        fun onDecrementCounter(counter: CounterDomain, position: Int)
        fun onShareCounter(counter: CounterDomain, position: Int)
        fun onDeleteCounter(counter: CounterDomain, position: Int)
    }

}