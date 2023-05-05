package com.smarthub.baseapplication.ui.fragments.rfequipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.RfDataListLayoutBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AssignACQTeamDAta
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.rfequipment.adapter.RfSurvayTeamFragAdapter
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurveyAssignTeam
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.widgets.CustomSpinner
import com.smarthub.baseapplication.widgets.CustomUserSpinner

class AssignRfTeamFragment(var rfSurvey:RfSurvey?, var parentIndex:Int?): BaseFragment(),RfSurvayTeamFragAdapter.RfListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var alertViewmodel: AlertViewModel
    lateinit var binding : RfDataListLayoutBinding
    lateinit var adapter:RfSurvayTeamFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = RfDataListLayoutBinding.inflate(inflater, container, false)
        alertViewmodel = ViewModelProvider(this)[AlertViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= RfSurvayTeamFragAdapter(this@AssignRfTeamFragment,this@AssignRfTeamFragment,rfSurvey)
        binding.listItem.adapter = adapter
        if (viewmodel.rfMainResponse?.hasActiveObservers() == true){
            viewmodel.rfMainResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.rfMainResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.RfSurvey!=null){
                AppLogger.log("SstSbcMainFrqagment card Data fetched successfully")
                hideLoader()
                adapter.setData(it.data.RfSurvey?.get(parentIndex!!)?.RfSurveyAssignTeam?.get(0))
                rfSurvey=it.data.RfSurvey?.get(parentIndex!!)
                if (rfSurvey!=null && rfSurvey?.RfSurveyAssignTeam?.isNotEmpty()==true){
                    if (rfSurvey?.RfSurveyAssignTeam?.get(0)?.GeographyLevel!=null)
                        viewmodel.getDepartment(rfSurvey?.RfSurveyAssignTeam?.get(0)?.GeographyLevel)
                }
            }else if (it!=null) {
                Toast.makeText(requireContext(),"SstSbcMainFrqagment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("SstSbcMainFrqagment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("SstSbcMainFrqagment Something went wrong")
                Toast.makeText(requireContext(),"SstSbcMainFrqagment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
        if (rfSurvey!=null && rfSurvey?.RfSurveyAssignTeam?.isNotEmpty()==true){
            if (rfSurvey?.RfSurveyAssignTeam?.get(0)?.GeographyLevel!=null)
                viewmodel.getDepartment(rfSurvey?.RfSurveyAssignTeam?.get(0)?.GeographyLevel)
        }
        viewmodel.fetchRfRequest(AppController.getInstance().siteid)
    }

    override fun onDestroy() {
        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true){
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun updateTeamClicked(data: RfSurveyAssignTeam) {
        showLoader()
        val dataModel = RfSurvey()
        dataModel.RfSurveyAssignTeam= arrayListOf(data)
        dataModel.id=rfSurvey?.id
        viewmodel.updateRfSurvey(dataModel)
        if (viewmodel.updateRfSurveyDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateRfSurveyDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateRfSurveyDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("AssignRfTeamFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.RfSurveyAssignTeam==200) {
                AppLogger.log("AssignRfTeamFragment card Data fetched successfully")
                viewmodel.fetchRfRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("AssignRfTeamFragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("AssignRfTeamFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("AssignRfTeamFragment Something went wrong")

            }
        }
    }
    override fun departmentTextSelected(department: String, userListText: CustomUserSpinner, executiveNumber: TextView, selectedName:String?) {
        alertViewmodel.getDepartmentUsers(GetUserList(department,
            AppController.getInstance().ownerName))
        if (alertViewmodel.userDataResponseLiveData.hasActiveObservers())
            alertViewmodel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
        alertViewmodel.userDataResponseLiveData.observe(viewLifecycleOwner){
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("AssignRfTeamFragment userDataResponseLiveData loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("AssignRfTeamFragment userDataResponseLiveData loaded successfull ")
                if (selectedName!=null)
                    userListText.setSpinnerDataByName(it.data,selectedName,null,executiveNumber)
                else
                    userListText.setSpinnerData(it.data,null,executiveNumber)
            }else AppLogger.log("Department not fetched")
        }

    }

    override fun geographyTextSelected(geograpgy: String, userListText: CustomSpinner, selectedItem: String?, ) {
        viewmodel.getDepartment(geograpgy)
        if (viewmodel.departmentDataDataResponse?.hasActiveObservers()==true)
            viewmodel.departmentDataDataResponse?.removeObservers(viewLifecycleOwner)
        viewmodel.departmentDataDataResponse?.observe(viewLifecycleOwner){
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("AssignRfTeamFragment departmentDataDataResponse loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("AssignRfTeamFragment departmentDataDataResponse loaded successfull ")
                if (selectedItem!=null)
                    userListText.setSpinnerData(it.data.Department.data,selectedItem)
                else
                    userListText.setSpinnerData(it.data.Department.data)
            }else AppLogger.log("Department not fetched")
        }
    }




}
