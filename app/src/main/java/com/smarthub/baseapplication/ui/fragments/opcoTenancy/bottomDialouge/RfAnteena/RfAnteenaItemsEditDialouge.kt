package com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.RfAnteena

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoOperationsTeamDialougeBinding
import com.smarthub.baseapplication.databinding.RfAnteenaListItemDialougeBinding

class RfAnteenaItemsEditDialouge (contentLayoutId: Int): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : RfAnteenaListItemDialougeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.canecl.setOnClickListener {
            dismiss()
        }
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RfAnteenaListItemDialougeBinding.inflate(inflater)
        return binding.root
    }
}