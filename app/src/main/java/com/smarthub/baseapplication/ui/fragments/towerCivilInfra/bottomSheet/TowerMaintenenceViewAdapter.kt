package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerMaintenenceViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.PreventiveMaintenance
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class TowerMaintenenceViewAdapter (var data: PreventiveMaintenance) : BaseBottomSheetDialogFragment(),
    ImageAttachmentAdapter.ItemClickListener {

    lateinit var binding: TowerMaintenenceViewDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }

        binding.vendorCode.text=data.VendorCode
        binding.remark.text=data.remark
        binding.ServiceType.text=data.ServiceType
        binding.NextPMInterval.text=data.NextPMInterval
        binding.VendorExecutiveName.text=data.VendorExecutiveName
        if(data.VendorCompany?.isNotEmpty()==true)
            AppPreferences.getInstance().setDropDown(binding.VendorName, DropDowns.VendorCompany.name,data.VendorCompany?.get(0).toString())
        binding.PMDate.text=Utils.getFormatedDate(data.PMDate,"dd-MMM-yyyy")
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TowerMaintenenceViewDialougeBinding.inflate(inflater)
        return binding.root
    }

    override fun itemClicked() {
//        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }


}