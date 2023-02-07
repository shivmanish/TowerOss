package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddnewRfequipmentDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class AddNewRfEquipmentAdapter (contentLayoutId: Int): BaseBottomSheetDialogFragment() {
    lateinit var binding : AddnewRfequipmentDialougeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddnewRfequipmentDialougeBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.8).toInt()
        binding.Cancle.setOnClickListener {
            dismiss()
        }
        setDatePickerView(binding.InatallationDate)

        binding.Submit.setOnClickListener {
            dismiss()
        }
        AppPreferences.getInstance().setDropDown(binding.technology,DropDowns.Technology.name)
        AppPreferences.getInstance().setDropDown(binding.ownerCompany,DropDowns.OwnerCompany.name)
        AppPreferences.getInstance().setDropDown(binding.userCompany,DropDowns.OemCompany.name)
        AppPreferences.getInstance().setDropDown(binding.oprationalStatus,DropDowns.OperationStatus.name)
        AppPreferences.getInstance().setDropDown(binding.rackSpaceUsed,DropDowns.RackSpaceUsed.name)
    }

    override fun getTheme() = R.style.NewDialogTask


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }
}