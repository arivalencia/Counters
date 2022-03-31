package com.ari.counters.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ari.counters.databinding.DialogErrorBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ErrorBottomSheet: BottomSheetDialogFragment() {

    private var _binding: DialogErrorBinding? = null
    private val binding: DialogErrorBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogErrorBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOk.setOnClickListener { dismiss() }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}