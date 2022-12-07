package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.BasicInfoDetailsBottomSheetBinding
import com.smarthub.baseapplication.databinding.RequestInfoBottomSheetDialogBinding

class RequestInfoBottomSheet(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : RequestInfoBottomSheetDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RequestInfoBottomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RequestInfoBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }

}