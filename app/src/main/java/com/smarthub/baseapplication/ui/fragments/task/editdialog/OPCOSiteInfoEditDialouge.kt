package com.smarthub.baseapplication.ui.fragments.task.editdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R

class OPCOSiteInfoEditDialouge (contentLayoutId: Int): BottomSheetDialogFragment(contentLayoutId) {
    lateinit var binding : OPCOSiteInfoEditDialouge

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* binding.cancel.setOnClickListener {
            dismiss()
        }*/
    }

    override fun getTheme() = R.style.NewDialogTask

/*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OPCOSiteInfoEditDialouge.inflate(inflater)
        return binding.root
    }
*/
}