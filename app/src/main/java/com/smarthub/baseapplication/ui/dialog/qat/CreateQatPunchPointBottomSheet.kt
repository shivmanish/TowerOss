package com.smarthub.baseapplication.ui.dialog.qat

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.util.Util
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CreateQatPunchPointLayoutBinding
import com.smarthub.baseapplication.databinding.LaunchQatLayoutBinding
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.qatcheck.QATMainLaunchNew
import com.smarthub.baseapplication.model.qatcheck.QalLaunchModel
import com.smarthub.baseapplication.model.qatcheck.punch_point.PunchPointUpdate
import com.smarthub.baseapplication.model.qatcheck.punch_point.QatPunchPointModel
import com.smarthub.baseapplication.model.register.dropdown.DropdownParam
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.QatMainModel
import com.smarthub.baseapplication.ui.alert.dialog.AlertUserListBottomSheet
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.widgets.CustomStringSpinner
import com.smarthub.baseapplication.widgets.CustomUserSpinner


class CreateQatPunchPointBottomSheet(var listener : LaunchQatBottomSheetListener,var QATItem_id :String) : BaseBottomSheetDialogFragment()  {

    lateinit var binding: CreateQatPunchPointLayoutBinding
    lateinit var alertViewModel: AlertViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = CreateQatPunchPointLayoutBinding.bind(view)
        alertViewModel = ViewModelProvider(requireActivity())[AlertViewModel::class.java]
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }
        setDatePickerView(binding.txtTargetDate)
        if (alertViewModel.departmentDropdown.hasActiveObservers())
            alertViewModel.departmentDropdown.removeObservers(viewLifecycleOwner)
        alertViewModel.departmentDropdown.observe(viewLifecycleOwner) {
            //response will get here
            if (it?.data != null) {
                binding.assigneeDepartment.setSpinnerData(it.data.department)
                if (it.data.department.isNotEmpty())
                    alertViewModel.getUser(GetUserList(it.data.department[0], AppController.getInstance().ownerName))
            }else Toast.makeText(requireContext(),"Department not fetched", Toast.LENGTH_LONG).show()
        }
        alertViewModel.getDepartments(DropdownParam("SMRT","department"))

        if (alertViewModel.userDataResponseLiveData.hasActiveObservers())
            alertViewModel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
        alertViewModel.userDataResponseLiveData.observe(viewLifecycleOwner, Observer {
            //response will get here
            if (it?.data != null) {
                binding.assignTo.setSpinnerData(it.data)
            }else Toast.makeText(requireContext(),"Department not fetched", Toast.LENGTH_LONG).show()
        })

        binding.assigneeDepartment.itemSelectedListener = object :CustomStringSpinner.ItemSelectedListener{
            override fun itemSelected(item: String) {
                if (item.isNotEmpty())
                    alertViewModel.getUser(GetUserList(item, AppController.getInstance().ownerName))
            }
        }

        binding.update.setOnClickListener {
            val item = PunchPointUpdate(
                AssignedTo = binding.assignTo.selectedValue.username,
                AssigneeDepartment = binding.assigneeDepartment.selectedValue,
                ClosedBy = binding.assignTo.selectedValue.username,
                QAT = QATItem_id,
                QATIssueType = "2",
                QATObservation = binding.txtObs.text.toString(),
                QATPunchPointStatus = "1",
                Recommendation = binding.txtRecmd.text.toString(),
                Resolution = "",
                ResolveDateTime = "",
                TargetDateTime = Utils.getCurrentFormatedDate(),
                isActive = true
            )
            val qATMainLaunchNew = ArrayList<PunchPointUpdate>()
            qATMainLaunchNew.add(item)
            val data = QatPunchPointModel(qATMainLaunchNew,AppController.getInstance().siteid,AppController.getInstance().ownerName)
            AppLogger.log("qatLaunchMain data:${Gson().toJson(data)}")
            listener.onPunchPointCreated(data)
           dismiss()
        }

    }

    override fun getTheme() = R.style.NewDialogTask

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CreateQatPunchPointLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

    interface LaunchQatBottomSheetListener{
        fun onPunchPointCreated(data :QatPunchPointModel)
    }
}