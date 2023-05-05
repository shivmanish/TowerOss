package com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.SiteAcqTeamNonSwitLayoutBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcObservation
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.ImageViewBottomSheet
import com.smarthub.baseapplication.ui.fragments.sstSbc.adapter.RfSurveyValidationFragAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class RfSurveyValidationFragment(var surveyValidationData: SstSbcAllData?, var parentIndex:Int): BaseFragment(),RfSurveyValidationFragAdapter.RfSurveyApprovalListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : SiteAcqTeamNonSwitLayoutBinding
    lateinit var adapter:RfSurveyValidationFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = SiteAcqTeamNonSwitLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= RfSurveyValidationFragAdapter(this@RfSurveyValidationFragment,this@RfSurveyValidationFragment,surveyValidationData)
        binding.listItem.adapter = adapter

        if (viewmodel.sstSbcModelResponse?.hasActiveObservers() == true) {
            viewmodel.sstSbcModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.sstSbcModelResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("RfSurveyValidationFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("RfSurveyValidationFragment card Data fetched successfully")
                hideLoader()
                try {
                    surveyValidationData=it.data.SstSbc?.get(parentIndex)
                    adapter.setData(it.data.SstSbc?.get(parentIndex)?.SstSbcObservation?.get(0))
                } catch (e: java.lang.Exception) {
                    AppLogger.log("RfSurveyValidationFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("RfSurveyValidationFragment size :${it.data.SstSbc?.size}")
            } else if (it != null) {
                AppLogger.log("RfSurveyValidationFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("RfSurveyValidationFragment Something went wrong")

            }
        }



//        binding.swipeLayout.setOnRefreshListener {
//            binding.swipeLayout.isRefreshing=false
//            viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
//        }
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
        val bm = AttachmentCommonDialogBottomSheet("SAcqAssignACQTeam",surveyValidationData?.SstSbcObservation?.get(0)?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }
    

    override fun updateRfSurveyApprovalClicked(data: SstSbcObservation) {
        showLoader()
        val dataModel = SstSbcAllData()
        dataModel.SstSbcObservation= arrayListOf(data)
        dataModel.id=surveyValidationData?.id
        viewmodel.updateSstSbc(dataModel)
        if (viewmodel.updateSstSbcDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSstSbcDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSstSbcDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("RfSurveyValidationFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SstSbcObservation==200) {
                AppLogger.log("RfSurveyValidationFragment card Data fetched successfully")
                viewmodel.fetchSstSbcModelRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("RfSurveyValidationFragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("RfSurveyValidationFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("RfSurveyValidationFragment Something went wrong")

            }
        }
    }




}
