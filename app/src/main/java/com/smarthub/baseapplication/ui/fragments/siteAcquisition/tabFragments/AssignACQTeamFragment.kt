package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.SiteAcqTeamNonSwitLayoutBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.AssignACQTeamDAta
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.model.taskModel.GeoGraphyLevelPostData
import com.smarthub.baseapplication.ui.alert.model.request.GetUserList
import com.smarthub.baseapplication.ui.alert.viewmodel.AlertViewModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AssignACQTeamFragAdapter
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.task.TaskViewModel
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.DropDowns
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.widgets.CustomSpinner
import com.smarthub.baseapplication.widgets.CustomUserSpinner

class AssignACQTeamFragment(var acqTeamData:NewSiteAcquiAllData?, var parentIndex:Int): BaseFragment(),AssignACQTeamFragAdapter.AssignACQTeamListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var alertViewmodel: AlertViewModel
    lateinit var binding : SiteAcqTeamNonSwitLayoutBinding
    lateinit var adapter:AssignACQTeamFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = SiteAcqTeamNonSwitLayoutBinding.inflate(inflater, container, false)
        alertViewmodel = ViewModelProvider(this)[AlertViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= AssignACQTeamFragAdapter(this@AssignACQTeamFragment,this@AssignACQTeamFragment,acqTeamData)
        binding.listItem.adapter = adapter

        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.siteAgreementModel?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                hideLoader()
                try {
                    acqTeamData=it.data.SAcqSiteAcquisition?.get(parentIndex)
                    adapter.setData(it.data.SAcqSiteAcquisition?.get(parentIndex)?.SAcqAssignACQTeam?.get(0))
                    if (acqTeamData!=null && acqTeamData?.SAcqAssignACQTeam?.isNotEmpty()==true){
                        if (acqTeamData?.SAcqAssignACQTeam?.get(0)?.GeographyLevel!=null)
                            viewmodel.getDepartment(acqTeamData?.SAcqAssignACQTeam?.get(0)?.GeographyLevel)
                    }
                } catch (e: java.lang.Exception) {
                    AppLogger.log("SiteAgreemnets Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("SiteAgreemnets size :${it.data.SAcqSiteAcquisition?.size}")
            } else if (it != null) {
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")

            }
        }



//        binding.swipeLayout.setOnRefreshListener {
//            binding.swipeLayout.isRefreshing=false
//            viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
//        }
        if (acqTeamData!=null && acqTeamData?.SAcqAssignACQTeam?.isNotEmpty()==true){
            if (acqTeamData?.SAcqAssignACQTeam?.get(0)?.GeographyLevel!=null)
                viewmodel.getDepartment(acqTeamData?.SAcqAssignACQTeam?.get(0)?.GeographyLevel)
        }
        viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
    }

    override fun onDestroy() {
        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true){
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun attachmentItemClicked() {
       AppLogger.log("Attachment clicked")
    }

    override fun addAttachment() {
        val bm = AttachmentCommonDialogBottomSheet("SAcqAssignACQTeam",acqTeamData?.SAcqAssignACQTeam?.get(0)?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun departmentTextSelected(department: String, userListText: CustomUserSpinner,executiveName: TextView,executiveNumber: TextView,selectedName:String?) {
        alertViewmodel.getDepartmentUsers(GetUserList(department,AppController.getInstance().ownerName))
        if (alertViewmodel.userDataResponseLiveData.hasActiveObservers())
            alertViewmodel.userDataResponseLiveData.removeObservers(viewLifecycleOwner)
        alertViewmodel.userDataResponseLiveData.observe(viewLifecycleOwner){
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment userDataResponseLiveData loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("SiteAgreemnets Fragment userDataResponseLiveData loaded successfull ")
                if (selectedName!=null)
                    userListText.setSpinnerDataByName(it.data,selectedName,executiveName,executiveNumber)
                else
                    userListText.setSpinnerData(it.data,executiveName,executiveNumber)
            }else AppLogger.log("Department not fetched")
        }

    }

    override fun geographyTextSelected(geograpgy: String, userListText: CustomSpinner, selectedItem: String?, ) {
        viewmodel.getDepartment(geograpgy)
        if (viewmodel.departmentDataDataResponse?.hasActiveObservers()==true)
            viewmodel.departmentDataDataResponse?.removeObservers(viewLifecycleOwner)
        viewmodel.departmentDataDataResponse?.observe(viewLifecycleOwner){
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment departmentDataDataResponse loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("SiteAgreemnets Fragment departmentDataDataResponse loaded successfull ")
                if (selectedItem!=null)
                    userListText.setSpinnerData(it.data.Department.data,selectedItem)
                else
                    userListText.setSpinnerData(it.data.Department.data)
            }else AppLogger.log("Department not fetched")
        }
    }

    override fun updateTeamClicked(data: AssignACQTeamDAta?) {
        showLoader()
        val dataModel = UpdateSiteAcquiAllData()
        val tempList:ArrayList<AssignACQTeamDAta> =ArrayList()
        tempList.clear()
        tempList.add(data!!)
        dataModel.SAcqAssignACQTeam=tempList
        dataModel.id=acqTeamData?.id
        viewmodel.updateSiteAcq(dataModel)
        if (viewmodel.updateSiteAcqDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqAssignACQTeam==200) {
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")

            }
        }
    }




}
