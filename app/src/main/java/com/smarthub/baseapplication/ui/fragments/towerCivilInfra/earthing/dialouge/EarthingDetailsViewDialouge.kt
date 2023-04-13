package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.EarthingDetailsViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilInfraEarthingDetail
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.DropDowns

class EarthingDetailsViewDialouge (var data: TwrCivilInfraEarthingDetail) : BaseBottomSheetDialogFragment(){

    lateinit var binding: EarthingDetailsViewDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        if (data.EarthingCableType!=null && data.EarthingCableType!!>0)
            AppPreferences.getInstance().setDropDown(binding.EarthingCableType,DropDowns.EarthingCableType.name,data.EarthingCableType.toString())
        if (data.PitRodMaterial!=null && data.PitRodMaterial!!>0)
            AppPreferences.getInstance().setDropDown(binding.PitRodMaterial,DropDowns.PitRodMaterial.name,data.PitRodMaterial.toString())
        binding.PitSizeL.text=data.SizeL
        binding.PitSizeB.text=data.SizeB
        binding.PitSizeH.text=data.SizeH
        binding.PitDepth.text=data.Height
        binding.EarthingCableLength.text=data.EarthingCableLength
        binding.locationMark.text=data.LocationMark
        binding.remark.text=data.remark
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = EarthingDetailsViewDialougeBinding.inflate(inflater)
        return binding.root
    }



}