package com.ari.counters.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ari.counters.R
import com.ari.counters.databinding.DialogAddCounterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddCounterBottomSheet:  BottomSheetDialogFragment() {

    private var _binding: DialogAddCounterBinding? = null
    private val binding: DialogAddCounterBinding get() = _binding!!
    private var listener: AddCounterListener? = null

    fun setListener(listener: AddCounterListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogAddCounterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOk.setOnClickListener {
            if (validateCounterTitle()) {
                listener?.onCreateCounter(getCounterTitle())
                dismiss()
            }
        }

    }

    private fun getCounterTitle(): String = binding.tvCounterTitle.text.toString()

    private fun validateCounterTitle(): Boolean {
        if (getCounterTitle().isEmpty() || getCounterTitle().isBlank()) {
            binding.tvCounterTitle.error = getString(R.string.required)
            return false
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    public interface AddCounterListener{
        fun onCreateCounter(counterTitle: String)
    }

}