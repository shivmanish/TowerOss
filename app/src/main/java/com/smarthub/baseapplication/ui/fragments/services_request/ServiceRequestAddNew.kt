package com.smarthub.baseapplication.ui.fragments.services_request

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ServiceRequestAddNewDialougeBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils

class ServiceRequestAddNew (contentLayoutId: Int): BaseBottomSheetDialogFragment() {
    lateinit var binding : ServiceRequestAddNewDialougeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ServiceRequestAddNewDialougeBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        setDatePickerView(binding.requestDate)
        setDatePickerView(binding.expectedDate)
        binding.Add.setOnClickListener {
            dismiss()
        }

        AppPreferences.getInstance().setDropDown(binding.srType,DropDowns.SRType.name,"1")
        AppPreferences.getInstance().setDropDown(binding.srStatus,DropDowns.SRStatus.name,"1")
        AppPreferences.getInstance().setDropDown(binding.requesterCompny,DropDowns.SRDetailRequesterCompany.name,"1")
        AppPreferences.getInstance().setDropDown(binding.technology,DropDowns.Technology.name,"1")
        AppPreferences.getInstance().setDropDown(binding.hubSite,DropDowns.Hubsite.name,"1")
        AppPreferences.getInstance().setDropDown(binding.priority,DropDowns.Priority.name,"1")
    }

    override fun getTheme() = R.style.NewDialogTask


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }
}