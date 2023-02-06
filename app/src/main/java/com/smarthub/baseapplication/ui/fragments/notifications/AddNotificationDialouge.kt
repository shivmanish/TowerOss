package com.smarthub.baseapplication.ui.fragments.notifications

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.AddNotificationDialougeBinding
import com.smarthub.baseapplication.databinding.TowerCivilAddEarthingBinding
import com.smarthub.baseapplication.databinding.TowerCivilAddTowerBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.notification.newData.AddNotificationModel
import com.smarthub.baseapplication.ui.alert.adapter.SelectCallBack
import com.smarthub.baseapplication.ui.alert.adapter.UserListAdapter
import com.smarthub.baseapplication.ui.alert.dialog.AlertUserListBottomSheet
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.dialog.BaseBottomSheetDialogFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AddNotificationDialouge (contentLayoutId: Int): BaseBottomSheetDialogFragment(contentLayoutId),SelectCallBack,SelectedUserListner {
    lateinit var binding : AddNotificationDialougeBinding
    private lateinit var homeViewModel: HomeViewModel
    lateinit var viewmodel: AlertViewModel
    var AssignToList= ArrayList<UserDataResponseItem>()
    lateinit var datamodel:AddNotificationModel
     var selectesPos=ArrayList<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = AddNotificationDialougeBinding.inflate(inflater)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        viewmodel = ViewModelProvider(requireActivity())[AlertViewModel::class.java]
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerLayout.layoutParams.height = (Utils.getScreenHeight()*0.65).toInt()
        datamodel= AddNotificationModel()
        binding.icMenuClose.setOnClickListener {
            dismiss()
        }

        binding.Add.setOnClickListener {
            dismiss()
        }
        if (homeViewModel.userDataListResponse?.hasActiveObservers() == true)
            homeViewModel.userDataListResponse?.removeObservers(viewLifecycleOwner)
        homeViewModel.userDataListResponse?.observe(viewLifecycleOwner){
            hideLoader()
            if (it?.data!=null && it.status == Resource.Status.SUCCESS){
                AssignToList.clear()
                AssignToList.addAll(it.data)
                viewmodel.userDataList.clear()
                viewmodel.userDataList.addAll(it.data!!)
                val bm = UserListBottomSheet(R.layout.alert_list_bottom_sheet, viewmodel, this,this)
                bm.show(childFragmentManager, "categoery")
                AppLogger.log("Users fetched successfully in NotificationFragment class ${it.data}")
            }else{
                AppLogger.log("something wend wrong in NotificationFragment class e:${it.message}")

            }
        }

        binding.users.setOnClickListener {
            showLoader()
            homeViewModel.getUsers()
        }


        binding.Add.setOnClickListener {
            homeViewModel.addNotification(datamodel)
        }

        if (homeViewModel.addNotiResponse?.hasActiveObservers() == true)
            homeViewModel.addNotiResponse?.removeObservers(viewLifecycleOwner)
        homeViewModel.addNotiResponse?.observe(viewLifecycleOwner){
            if (it!=null){
                if (it.status == Resource.Status.LOADING){
                    showProgressLayout()
                }else{
                    hideProgressLayout()
                }
                if (it.status == Resource.Status.SUCCESS && it.data?.success == true){
                    AppLogger.log("Notification Sent Successfully")
                    dismiss()
//                    homeViewModel.opcoTenancyRequestAll(AppController.getInstance().siteid)
                }else{
                    AppLogger.log("UnExpected Error found")
                }
            }else{
                AppLogger.log("Something went wrong")
            }
        }

    }

    override fun getTheme() = R.style.NewDialogTask


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
        dialog.behavior.skipCollapsed = false
        return dialog
    }

    fun showProgressLayout(){
        if (binding.progressLayout.visibility != View.VISIBLE)
            binding.progressLayout.visibility = View.VISIBLE
    }
    fun hideProgressLayout(){
        if (binding.progressLayout.visibility == View.VISIBLE)
            binding.progressLayout.visibility = View.GONE
    }

    override fun onSelectUser(position: Int, isadd: Boolean) {
        if (isadd) {
            viewmodel.selecteduserposition.add(position)
            selectesPos.add(position)
        } else {
            viewmodel.selecteduserposition.remove(position)
            selectesPos.remove(position)
        }

        AppLogger.log("Selected Pos=====>: $selectesPos ")

    }

    override fun selectedUser() {
        binding.users.text=""
        for (i in selectesPos){
            if (binding.users.text.isEmpty())
                binding.users.text="${AssignToList[i].first_name} ${AssignToList[i].last_name}"
            else
                binding.users.text= "${binding.users.text},"+ "${AssignToList[i].first_name}  ${AssignToList[i].last_name}"
            AppLogger.log("Selected value=====>: ${AssignToList[i].first_name}  ${AssignToList[i].last_name}")
        }
    }
}