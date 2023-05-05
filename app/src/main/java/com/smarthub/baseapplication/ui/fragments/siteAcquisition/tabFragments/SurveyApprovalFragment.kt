package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.SiteAcqTeamNonSwitLayoutBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqSiteApproval
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageViewBottomSheet
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.SurveyValidationFragAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SurveyApprovalFragment(var surveyApprovalData:NewSiteAcquiAllData?, var parentIndex:Int): BaseFragment(),SurveyValidationFragAdapter.SurveyApprovalListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : SiteAcqTeamNonSwitLayoutBinding
    lateinit var adapter:SurveyValidationFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = SiteAcqTeamNonSwitLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= SurveyValidationFragAdapter(this@SurveyApprovalFragment,this@SurveyApprovalFragment,surveyApprovalData)
        binding.listItem.adapter = adapter

        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.siteAgreementModel?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SurveyApprovalFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("SurveyApprovalFragment card Data fetched successfully")
                hideLoader()
                try {
                    surveyApprovalData=it.data.SAcqSiteAcquisition?.get(parentIndex)
                    adapter.setData(it.data.SAcqSiteAcquisition?.get(parentIndex)?.SAcqSiteApproval?.get(0))
                } catch (e: java.lang.Exception) {
                    AppLogger.log("SurveyApprovalFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("SurveyApprovalFragment size :${it.data.SAcqSiteAcquisition?.size}")
            } else if (it != null) {
                AppLogger.log("SurveyApprovalFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SurveyApprovalFragment Something went wrong")

            }
        }



//        binding.swipeLayout.setOnRefreshListener {
//            binding.swipeLayout.isRefreshing=false
//            viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
//        }
        if (surveyApprovalData!=null && surveyApprovalData?.SAcqAssignACQTeam?.isNotEmpty()==true){
            if (surveyApprovalData?.SAcqAssignACQTeam?.get(0)?.GeographyLevel!=null)
                viewmodel.getDepartment(surveyApprovalData?.SAcqAssignACQTeam?.get(0)?.GeographyLevel)
        }
        viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
    }

    override fun onDestroy() {
        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true){
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun attachmentItemClicked(item: Attachments) {
        val bm = ImageViewBottomSheet(item)
        bm.show(childFragmentManager,"sdg")
    }

    override fun addAttachment() {
        val bm = AttachmentCommonDialogBottomSheet("SAcqAssignACQTeam",surveyApprovalData?.SAcqAssignACQTeam?.get(0)?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }
    

    override fun updateSurveyApprovalClicked(data: SAcqSiteApproval) {
        showLoader()
        val dataModel = UpdateSiteAcquiAllData()
        dataModel.SAcqSiteApproval= arrayListOf(data)
        dataModel.id=surveyApprovalData?.id
        viewmodel.updateSiteAcq(dataModel)
        if (viewmodel.updateSiteAcqDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SurveyApprovalFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqSiteAcquisition==200) {
                AppLogger.log("SurveyApprovalFragment card Data fetched successfully")
                viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("SurveyApprovalFragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SurveyApprovalFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SurveyApprovalFragment Something went wrong")

            }
        }
    }




}
