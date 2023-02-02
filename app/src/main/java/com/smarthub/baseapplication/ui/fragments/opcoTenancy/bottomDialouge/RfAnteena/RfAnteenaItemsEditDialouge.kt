package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.RfAnteena

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoOperationsTeamDialougeBinding
import com.smarthub.baseapplication.databinding.RfAnteenaListItemDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.RfAnteenaData
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class RfAnteenaItemsEditDialouge (contentLayoutId: Int,var rfAntenadata : RfAnteenaData,var id:String,var listner:rfAntenaListener): BaseBottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : RfAnteenaListItemDialougeBinding


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RfAnteenaListItemDialougeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.85).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        setDatePickerView(binding.editInstallationDate)
        binding.editAntennaSerialNumber.text=rfAntenadata.AntenaSerialNumber.toEditable()
        binding.editAntennaMake.text=rfAntenadata.AntenaMake.toEditable()
        binding.antennaModel.text=rfAntenadata.AntenaModel.toEditable()
        binding.installedHeight.text=rfAntenadata.InstalledHeight.toEditable()
        binding.antennaLenth.text=rfAntenadata.AntenaSizeL.toEditable()
        binding.antennaBidth.text=rfAntenadata.AntenaSizeB.toEditable()
        binding.antennaHeight.text=rfAntenadata.AntenaSizeH.toEditable()
        binding.AntennaWeight.text=rfAntenadata.AntenaWeight.toEditable()
        binding.AntennaOrientation.text=rfAntenadata.AntenaOrentiation.toEditable()
        binding.AntennaTilt.text=rfAntenadata.AntenaTilt.toEditable()

//        AppPreferences.getInstance().setDropDown(binding.userCompany,DropDowns.UserCompany.name,rfAntenadata.UserCompany)
    }

    override fun getTheme() = R.style.NewDialogTask

    interface rfAntenaListener{
        fun updatedData(data : RfAnteenaData)
    }

}