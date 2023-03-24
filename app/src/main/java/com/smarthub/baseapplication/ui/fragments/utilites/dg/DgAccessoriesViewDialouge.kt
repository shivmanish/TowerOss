package com.smarthub.baseapplication.ui.fragments.utilites.dg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.DgAccessoriesViewDialougeBinding
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityAccessory
import com.smarthub.baseapplication.utils.Utils

class DgAccessoriesViewDialouge (contentLayoutId: Int, var data: UtilityAccessory) : BottomSheetDialogFragment(contentLayoutId){

    lateinit var binding: DgAccessoriesViewDialougeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DgAccessoriesViewDialougeBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
        binding.ItemName.text=data.ItemName
        binding.Type.text=data.Type
        binding.Make.text=data.Make
        binding.Model.text=data.Model
        binding.Quantity.text=data.Quantity
        binding.remark.text=data.Remark
        binding.InstallationDate.text=Utils.getFormatedDate(data.InstallationDate,"dd-MMM-yyyy")


    }

    override fun getTheme() = R.style.NewDialogTask




}