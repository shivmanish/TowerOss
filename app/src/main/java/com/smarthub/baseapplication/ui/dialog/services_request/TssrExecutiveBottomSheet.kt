package com.smarthub.baseapplication.ui.dialog.services_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TssrExecutiveBottomSheetDialogBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.serviceRequest.opcoTssr.TSSRExecutiveInfo
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TssrExecutiveBottomSheet(
    contentLayoutId: Int,
    Id: String?,
    viewmodel: HomeViewModel,
    tssrExcutive: TSSRExecutiveInfo,
    serviceRequestAllData: ServiceRequestAllDataItem?
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : TssrExecutiveBottomSheetDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = TssrExecutiveBottomSheetDialogBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = TssrExecutiveBottomSheetDialogBinding.inflate(inflater)
        return binding.root
    }
}