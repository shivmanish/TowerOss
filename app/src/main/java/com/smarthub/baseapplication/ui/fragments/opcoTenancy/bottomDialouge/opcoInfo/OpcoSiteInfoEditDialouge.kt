package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoSiteDialougeLayoutBinding
import com.smarthub.baseapplication.databinding.RfEquipmentListItemDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class OpcoSiteInfoEditDialouge (contentLayoutId: Int,var opcoInfo: Opcoinfo): BaseBottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : OpcoInfoSiteDialougeLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpcoInfoSiteDialougeLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.85).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }

        binding.OPCOName.text=opcoInfo.OpcoName.toEditable()
        binding.OPCOSiteID.text=opcoInfo.OpcoSiteID.toEditable()
        binding.OPCOSiteName.text=opcoInfo.OpcoSiteName.toEditable()
        binding.CommittedNWA.text=opcoInfo.committedNWA
        binding.RFIAcceptanceDate.text=opcoInfo.rfiAcceptanceDate
        binding.RFRDate.text=opcoInfo.rfrDate
        binding.OPCOSignOffDate.text=opcoInfo.Opcosignoffdate
        AppPreferences.getInstance().setDropDownByName(binding.opcoSiteStatus,DropDowns.Opcositestatus.name,opcoInfo.Opcositestatus)
        AppPreferences.getInstance().setDropDownByName(binding.opcoSiteType,DropDowns.Opcositetype.name,opcoInfo.Opcositetype)
        AppPreferences.getInstance().setDropDownByName(binding.opcoNetworkType,DropDowns.Operatornetworktype.name,opcoInfo.Operatornetworktype)
        AppPreferences.getInstance().setDropDownByName(binding.AlarmExtension,DropDowns.Alarmsextension.name,opcoInfo.Alarmsextension)
        AppPreferences.getInstance().setDropDownByName(binding.RFTechnology,DropDowns.Rftechnology.name,opcoInfo.Rftechnology)
        AppPreferences.getInstance().setDropDownByName(binding.TelecomEquipmentType,DropDowns.Telecomequipmenttype.name,opcoInfo.Telecomequipmenttype)
        AppPreferences.getInstance().setDropDownByName(binding.RRUCount,DropDowns.Rrucount.name,opcoInfo.Rrucount)
        AppPreferences.getInstance().setDropDownByName(binding.SectorCount,DropDowns.Sectorcount.name,opcoInfo.Sectorcount)
        AppPreferences.getInstance().setDropDownByName(binding.RackCount,DropDowns.Rackcount.name,opcoInfo.Rackcount)
        AppPreferences.getInstance().setDropDownByName(binding.AnteenaCount,DropDowns.Antenacount.name,opcoInfo.Antenacount)
        AppPreferences.getInstance().setDropDownByName(binding.AnteenaSlotUsed,DropDowns.Antenaslotused.name,opcoInfo.Antenaslotused)

        setDatePickerView(binding.RFIAcceptanceDate)
        setDatePickerView(binding.RFRDate)
        setDatePickerView(binding.OPCOSignOffDate)
    }

    override fun getTheme() = R.style.NewDialogTask


}