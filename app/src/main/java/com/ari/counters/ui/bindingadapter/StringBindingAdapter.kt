package com.ari.counters.ui.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ari.counters.R

@BindingAdapter("count")
fun setCount(tv: TextView, count: Int?) {
    tv.text = tv.context.getString(R.string.count_n_) + (count ?: 0)
}