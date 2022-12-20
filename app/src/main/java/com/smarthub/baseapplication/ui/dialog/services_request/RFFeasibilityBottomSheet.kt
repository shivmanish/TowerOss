package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerMsbBottomSheetDialogBinding
import com.smarthub.baseapplication.databinding.RfFeasibilityBottomSheetDialogBinding
import com.smarthub.baseapplication.model.serviceRequest.RFFeasibility
import com.smarthub.baseapplication.model.serviceRequest.RequesterInfo

class RFFeasibilityBottomSheet(contentLayoutId: Int) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : RfFeasibilityBottomSheetDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = RfFeasibilityBottomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        var rfFeasibility: RFFeasibility? =null
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RfFeasibilityBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }
}