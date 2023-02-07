package com.smarthub.baseapplication.ui.alert.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.databinding.AlertListBottomSheetBinding
import com.smarthub.baseapplication.ui.alert.adapter.SelectCallBack
import com.smarthub.baseapplication.ui.alert.adapter.UserListAdapter
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils

class AlertUserListBottomSheet(contentLayoutId: Int, var viewModel: AlertViewModel,var listner:SelectCallBack) : BaseBottomSheetDialogFragment() {
    lateinit var binding: AlertListBottomSheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AlertListBottomSheetBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.90).toInt()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        AppLogger.log("size :${viewModel.userDataList.size}")
        binding.recyclerView.adapter = UserListAdapter(viewModel.userDataList,viewModel.selecteduserposition, listner )

        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.close.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = true
        return dialog
    }
}