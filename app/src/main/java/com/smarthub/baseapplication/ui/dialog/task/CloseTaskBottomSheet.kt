package com.smarthub.baseapplication.ui.dialog.task

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.CloseTaskBottomSheetBinding
import com.smarthub.baseapplication.databinding.CloseTaskLayoutBinding
import com.smarthub.baseapplication.databinding.LaunchQatLayoutBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.qatcheck.QATMainLaunchNew
import com.smarthub.baseapplication.model.qatcheck.QalLaunchModel
import com.smarthub.baseapplication.model.register.dropdown.DropdownParam
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QatMainModel
import com.smarthub.baseapplication.ui.alert.dialog.AlertUserListBottomSheet
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.dialog.qat.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.widgets.CustomSpinner
import com.smarthub.baseapplication.widgets.CustomStringSpinner
import com.smarthub.baseapplication.widgets.CustomUserSpinner


class CloseTaskBottomSheet(var taskId:String) : BaseBottomSheetDialogFragment()  {

    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: CloseTaskLayoutBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = CloseTaskLayoutBinding.bind(view)
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
       binding.update.setOnClickListener {
           showLoader()
           homeViewModel.closeTask(taskId,binding.Remark.text.toString())
        }

        if (homeViewModel.closeTaskModel?.hasActiveObservers() == true)
            homeViewModel.closeTaskModel?.removeObservers(viewLifecycleOwner)
        homeViewModel.closeTaskModel?.observe(viewLifecycleOwner) {
            hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                if (it.data.Error.isEmpty() || it.data.Message=="Data updated"){
                    dismiss()
                    Toast.makeText(requireContext(),it.data.Message,Toast.LENGTH_SHORT).show()
                    val intent = Intent (requireContext(), DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }else{
                    Toast.makeText(requireContext(),it.data.Error,Toast.LENGTH_SHORT).show()
                }
            }else Toast.makeText(requireContext(),"Task not closed",Toast.LENGTH_SHORT).show()
        }

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CloseTaskLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

    interface CloseTaskBottomSheetListener{
        fun onTaskClosed(remark :String)
    }

}