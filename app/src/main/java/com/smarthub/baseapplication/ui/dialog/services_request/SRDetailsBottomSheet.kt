package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BasicInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.databinding.SrDetailsBottomSheetDialogBinding

class SRDetailsBottomSheet(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding : SrDetailsBottomSheetDialogBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SrDetailsBottomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SrDetailsBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

}