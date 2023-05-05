package com.smarthub.baseapplication.ui.fragments.rfequipment

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
import com.smarthub.baseapplication.ui.fragments.rfequipment.adapter.RfValidationFragAdapter
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey2
import com.smarthub.baseapplication.ui.fragments.sstSbc.adapter.RfSurveyValidationFragAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class RfValidationFragment(var surveyValidationData: RfSurvey?, var parentIndex:Int): BaseFragment(),RfValidationFragAdapter.RfSurveyValidationListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : SiteAcqTeamNonSwitLayoutBinding
    lateinit var adapter:RfValidationFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = SiteAcqTeamNonSwitLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= RfValidationFragAdapter(this@RfValidationFragment,this@RfValidationFragment,surveyValidationData)
        binding.listItem.adapter = adapter

        if (viewmodel.rfMainResponse?.hasActiveObservers() == true) {
            viewmodel.rfMainResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.rfMainResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("RfValidationFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("RfValidationFragment card Data fetched successfully")
                hideLoader()
                try {
                    surveyValidationData=it.data.RfSurvey?.get(parentIndex)
                    adapter.setData(it.data.RfSurvey?.get(parentIndex)?.RfSurvey2?.get(0))
                } catch (e: java.lang.Exception) {
                    AppLogger.log("RfValidationFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("RfValidationFragment size :${it.data.RfSurvey?.size}")
            } else if (it != null) {
                AppLogger.log("RfValidationFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("RfValidationFragment Something went wrong")

            }
        }



//        binding.swipeLayout.setOnRefreshListener {
//            binding.swipeLayout.isRefreshing=false
//            viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
//        }
        viewmodel.fetchRfRequest(AppController.getInstance().siteid)
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
        val bm = AttachmentCommonDialogBottomSheet("SAcqAssignACQTeam",surveyValidationData?.RfSurvey2?.get(0)?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }
    

    override fun RfSurveyValidationClicked(data: RfSurvey2) {
        showLoader()
        val dataModel = RfSurvey()
        dataModel.RfSurvey2= arrayListOf(data)
        dataModel.id=surveyValidationData?.id
        viewmodel.updateRfSurvey(dataModel)
        if (viewmodel.updateRfSurveyDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateRfSurveyDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateRfSurveyDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("RfValidationFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.RfSurvey==200) {
                AppLogger.log("RfValidationFragment card Data fetched successfully")
                viewmodel.fetchRfRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("RfValidationFragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("RfValidationFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("RfValidationFragment Something went wrong")

            }
        }
    }




}
