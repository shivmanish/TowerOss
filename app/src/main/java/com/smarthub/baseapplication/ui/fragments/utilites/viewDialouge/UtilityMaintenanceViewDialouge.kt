package com.smarthub.baseapplication.ui.fragments.utilites.viewDialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.UtilityMaintenanceViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityPreventiveMaintenance
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class UtilityMaintenanceViewDialouge (contentLayoutId: Int, var data: UtilityPreventiveMaintenance) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: UtilityMaintenanceViewDialougeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = UtilityMaintenanceViewDialougeBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        binding.ServiceType.text=data.ServiceType
        binding.VendorCode.text=data.VendorCode
        binding.NextPMInterval.text=data.NextPMInterval
        binding.VendorExecutiveName.text=data.VendorExecutiveName
        binding.remark.text=data.remark
        if (data.VendorCompany?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.vendorName, DropDowns.VendorCompany.name,data.VendorCompany?.get(0).toString())

        binding.PmDate.text=Utils.getFormatedDate(data.PMDate,"dd-MMM-yyyy")

    }

    override fun getTheme() = R.style.NewDialogTask




}