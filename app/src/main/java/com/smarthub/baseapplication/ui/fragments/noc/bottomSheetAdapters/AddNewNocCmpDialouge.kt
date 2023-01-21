package com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocCompAddnewDialougeBinding
import com.smarthub.baseapplication.databinding.PlandesignAddnewDialougeBinding
import com.smarthub.baseapplication.databinding.TowerCivilAddEarthingBinding
import com.smarthub.baseapplication.databinding.TowerCivilAddTowerBinding
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.Utils

class AddNewNocCmpDialouge (contentLayoutId: Int): BaseBottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : NocCompAddnewDialougeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = NocCompAddnewDialougeBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.7).toInt()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.Add.setOnClickListener {
            dismiss()
        }
        setDatePickerView(binding.IssueDate)
        setDatePickerView(binding.expiryDate)
        setDatePickerView(binding.applicationDate)
    }

    override fun getTheme() = R.style.NewDialogTask


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }
}