package com.smarthub.baseapplication.ui.dialog.services_request

import com.smarthub.baseapplication.model.serviceRequest.SPApproval
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SpApprovalDialogBinding
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SpApprovalBottomSheet(
    contentLayoutId: Int,
    viewmodel: HomeViewModel,
    Id: String?,
    var spApproval: SPApproval,
    var serviceRequestAllData: ServiceRequestAllDataItem
) : BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : SpApprovalDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = SpApprovalDialogBinding.bind(view)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()

        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        try{
            if (spApproval != null) {
                spApproval.let {
                    binding.editSPSubmissionDate.setText(it!!.SPSubmissionDate)
                    binding.editSPApprovalDate.setText(it!!.SPApprovalDate)
                    binding.editApprovedBy!!.setText(it!!.ApprovedBy)
                    binding.editApprovedEmail!!.setText( it!!.ApproverEmailID)
                }
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = SpApprovalDialogBinding.inflate(inflater)
        return binding.root
    }
}