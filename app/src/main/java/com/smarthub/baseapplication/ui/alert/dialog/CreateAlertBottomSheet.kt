package com.smarthub.baseapplication.ui.alert.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CreateAlertDialogBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.alert.adapter.AlertImageAdapter
import com.smarthub.baseapplication.utils.Utils


class CreateAlertBottomSheet (contentLayoutId: Int): BottomSheetDialogFragment(contentLayoutId) , ImageAttachmentAdapter.ItemClickListener {
    lateinit var binding : CreateAlertDialogBinding
    lateinit var myAdapter : AlertImageAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.constraint.layoutParams.height = (Utils.getScreenHeight()*0.75).toInt()
        binding.canecl.setOnClickListener {
            dismiss()
        }
        binding.rvAlertImageList.adapter = myAdapter
        binding.textChat.setOnClickListener {
//            val bottomSheetDialogFragment = ChetBottomSheet(R.layout.chat_dialog)
//            bottomSheetDialogFragment.show(childFragmentManager,"category")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = CreateAlertDialogBinding.inflate(inflater)
        myAdapter = AlertImageAdapter(this@CreateAlertBottomSheet)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = true
        return dialog
    }

    override fun itemClicked() {

    }

//    override fun itemAdded() {
//        AppLogger.log("itemAdded called")
//        myAdapter.addItem()
//    }

    override fun getTheme() = R.style.NewDialogTask
}