package com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AcqPropertyOwnerViewDialougeBinding
import com.smarthub.baseapplication.databinding.InsidePremisesViewDialougeBinding
import com.smarthub.baseapplication.databinding.TowerConsumableViewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqInsidePremise
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPropertyOwnerDetail
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class PropertyOwnerViewDialouge (contentLayoutId: Int, var data: SAcqPropertyOwnerDetail) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: AcqPropertyOwnerViewDialougeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        if (data.PropertyOwnership.isNotEmpty())
            AppPreferences.getInstance().setDropDown(binding.PropertyOwnership, DropDowns.PropertyOwnership.name,data.PropertyOwnership.get(0).toString())

        binding.Share.text=data.Share
        binding.OwnerName.text=data.OwnerName
        binding.PhNumber.text=data.PhoneNumber
        binding.EmailId.text=data.EmailId
        binding.Address.text=data.Address
        binding.remark.text=data.remark

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AcqPropertyOwnerViewDialougeBinding.inflate(inflater)
        return binding.root
    }



}