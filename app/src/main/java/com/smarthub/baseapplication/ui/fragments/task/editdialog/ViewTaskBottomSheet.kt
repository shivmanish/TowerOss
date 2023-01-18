package com.smarthub.baseapplication.ui.fragments.task.editdialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CloseTaskBottomSheetBinding
import com.smarthub.baseapplication.model.home.MyTeamTask
import com.smarthub.baseapplication.ui.dialog.siteinfo.pojo.BasicinfoModel
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ViewTaskBottomSheet(contentLayoutId: Int, val task: MyTeamTask, var viewModel: HomeViewModel) : BottomSheetDialogFragment(contentLayoutId) {

    lateinit var binding: CloseTaskBottomSheetBinding
    var basicinfoModel: BasicinfoModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.70).toInt()
        basicinfoModel = BasicinfoModel()
        binding = CloseTaskBottomSheetBinding.bind(view)

        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        binding.closeTaskBtn.setOnClickListener {
            dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (viewModel.basicinfoModel?.hasActiveObservers() == true)
            viewModel.basicinfoModel?.removeObservers(viewLifecycleOwner)
    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CloseTaskBottomSheetBinding.inflate(inflater)
        return binding.root
    }

//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val dialog = BottomSheetDialog(requireContext(), theme)
//        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
//        dialog.behavior.skipCollapsed = false
//        return dialog
//    }

}