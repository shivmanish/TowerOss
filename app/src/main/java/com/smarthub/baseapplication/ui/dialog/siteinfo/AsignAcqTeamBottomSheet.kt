package com.smarthub.baseapplication.ui.dialog.siteinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AssignAcqTeamBottomSheetBinding
import com.smarthub.baseapplication.model.serviceRequest.AssignACQTeamTeam
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment

class AsignAcqTeamBottomSheet(contentLayoutId: Int,var AssignAcqTeamDetailsData : AssignACQTeamTeam?) : BaseBottomSheetDialogFragment() {

    lateinit var binding : AssignAcqTeamBottomSheetBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AssignAcqTeamBottomSheetBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        AssignAcqTeamDetailsData.let {
//            binding.AcquisitionMode.text=AssignAcqTeamDetailsData?.AcquistionMode
//            binding.AcquisitionType.text=AssignAcqTeamDetailsData?.AcquisitionType
//            binding.PONumber.text=AssignAcqTeamDetailsData?.PONumber
//            binding.VendorName.text=AssignAcqTeamDetailsData?.PONumber

            binding.AcquisitionExecutiveName.text=AssignAcqTeamDetailsData?.ExecutiveName?.toEditable()
            binding.ExecutiveEmailID.text=AssignAcqTeamDetailsData?.ExecutiveEmailId?.toEditable()
            binding.ExecutiveNumber.text=AssignAcqTeamDetailsData?.ExecutiveMobile?.toEditable()
            binding.AcquisitionLeadName.text=AssignAcqTeamDetailsData?.LeadName?.toEditable()
            binding.LeadEmailId.text=AssignAcqTeamDetailsData?.LeadEmailId?.toEditable()
            binding.Number.text=AssignAcqTeamDetailsData?.LeadMobile?.toEditable()

            binding.AcquisitionBudget.text= AssignAcqTeamDetailsData?.AcquisitionBudget?.toEditable()
            binding.AcquisitionTargetDate.text=AssignAcqTeamDetailsData?.AcquisitionTargetDate

            binding.PoAmount.text=AssignAcqTeamDetailsData?.POAmount?.toEditable()
            binding.VendorExcutiveName.text=AssignAcqTeamDetailsData?.VendorExecutiveName?.toEditable()
            binding.VendorExecutiveEmail.text=AssignAcqTeamDetailsData?.VendorExecutiveEmailId?.toEditable()
            binding.VendorExecutiveNumber.text=AssignAcqTeamDetailsData?.VendorExecutiveMobile?.toEditable()
            binding.OfficeAddress.text=AssignAcqTeamDetailsData?.OfficeAddress?.toEditable()
            binding.Remmarks.text=AssignAcqTeamDetailsData?.Remark?.toEditable()
        }
        
    }
    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AssignAcqTeamBottomSheetBinding.inflate(inflater)
        return binding.root
    }

}