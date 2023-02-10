package com.smarthub.baseapplication.ui.dialog.services_request

import com.smarthub.baseapplication.model.serviceRequest.SODetail
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SoApprovalDialogBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SoApprovalBottomSheet(
    contentLayoutId: Int,
    var Id: String?,
    var viewmodel: HomeViewModel,
    var soDetail: SODetail,
    var serviceRequestAllData: ServiceRequestAllDataItem
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding: SoApprovalDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SoApprovalDialogBinding.bind(view)
        // binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()

        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        try {
            if (soDetail != null) {
                soDetail.let {
                    binding.sonumber.setText(it!!.SONumber)
                    binding.txtSoDate.setText(it!!.SODate)
                    binding.txtSOValue.setText(it!!.SOValue)
                    binding.txtSOLine.setText(it!!.SOLineItemNo)
                    binding.txtRemarks.setText(it!!.Remark)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SoApprovalDialogBinding.inflate(inflater)
        return binding.root
    }
}