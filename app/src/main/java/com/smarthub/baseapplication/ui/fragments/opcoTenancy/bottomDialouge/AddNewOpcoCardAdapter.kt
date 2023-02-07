package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddNewCardOpcoTenencyBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.DropDowns

class AddNewOpcoCardAdapter (contentLayoutId: Int): BaseBottomSheetDialogFragment() {
    lateinit var binding : AddNewCardOpcoTenencyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddNewCardOpcoTenencyBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        setDatePickerView(binding.OPCOSignOffDate)
        setDatePickerView(binding.RFRDate)
        setDatePickerView(binding.RFIAcceptanceDate)
        AppPreferences.getInstance().setDropDown(binding.opcoSiteStatus, DropDowns.Opcositestatus.name,"1")
        AppPreferences.getInstance().setDropDown(binding.opcoSiteType, DropDowns.Opcositetype.name,"1")
        AppPreferences.getInstance().setDropDown(binding.opcoNetworkType, DropDowns.Operatornetworktype.name,"1")
        AppPreferences.getInstance().setDropDown(binding.AlarmExtension, DropDowns.Alarmsextension.name,"1")
        AppPreferences.getInstance().setDropDown(binding.RFTechnology, DropDowns.Rftechnology.name,"1")
        AppPreferences.getInstance().setDropDown(binding.TelecomEquipmentType,DropDowns.Telecomequipmenttype.name,"1")
        AppPreferences.getInstance().setDropDown(binding.RRUCount, DropDowns.Rrucount.name,"1")
        AppPreferences.getInstance().setDropDown(binding.SectorCount, DropDowns.Sectorcount.name,"1")
        AppPreferences.getInstance().setDropDown(binding.RackCount, DropDowns.Rackcount.name,"1")
        AppPreferences.getInstance().setDropDown(binding.AnteenaCount, DropDowns.Antenacount.name,"1")
        AppPreferences.getInstance().setDropDown(binding.AnteenaSlotUsed, DropDowns.Antenaslotused.name,"1")

    }

    override fun getTheme() = R.style.NewDialogTask


}