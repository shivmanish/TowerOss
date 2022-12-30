package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoSiteDialougeLayoutBinding
import com.smarthub.baseapplication.databinding.RfEquipmentListItemDialougeBinding
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment

class OpcoSiteInfoEditDialouge (contentLayoutId: Int): BaseBottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : OpcoInfoSiteDialougeLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpcoInfoSiteDialougeLayoutBinding.inflate(inflater)
        return binding.root
    }
}