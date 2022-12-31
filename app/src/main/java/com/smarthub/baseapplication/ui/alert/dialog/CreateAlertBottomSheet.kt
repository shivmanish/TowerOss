package com.smarthub.baseapplication.ui.alert.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CreateAlertDialogBinding


class CreateAlertBottomSheet (contentLayoutId: Int): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : CreateAlertDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreateAlertDialogBinding.inflate(inflater)
        return binding.root
    }
}