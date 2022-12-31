package com.smarthub.baseapplication.ui.alert.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.Ac2TabUtilitiesFragmentBinding.inflate
import com.smarthub.baseapplication.databinding.CreateAlertDialogBinding
import com.smarthub.baseapplication.databinding.NextOpcoTechnicalDialogBinding
import com.smarthub.baseapplication.databinding.OpcoInfoSiteDialougeLayoutBinding
import com.smarthub.baseapplication.databinding.TaskSiteInfoDialougeLayoutBinding
import com.smarthub.baseapplication.databinding.TaskSiteInfoItemViewBinding
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo.OpcoSiteInfoEditDialouge

class CreateAlertBottomSheet (contentLayoutId: Int): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : CreateAlertDialogBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
    }
    override fun getTheme() = R.style.NewDialogTask
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreateAlertDialogBinding.inflate(inflater)
        return binding.root
    }
}