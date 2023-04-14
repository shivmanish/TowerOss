package com.smarthub.baseapplication.ui.fragments.siteAcquisition.tabFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionFragBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.*
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AcqSurveyFragAdapter
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AcquisitionSurveyFragment(var acqSurveyData:NewSiteAcquiAllData?, var parentIndex:Int): BaseFragment(),AcqSurveyFragAdapter.AcqSurveyListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : PowerConnectionFragBinding
    lateinit var adapter:AcqSurveyFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = PowerConnectionFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= AcqSurveyFragAdapter(this@AcquisitionSurveyFragment,this@AcquisitionSurveyFragment,acqSurveyData)
        binding.listItem.adapter = adapter

        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.siteAgreementModel?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                showLoader()
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                hideLoader()
                AppLogger.log("SiteAgreemnets AqcSurvey Data fetched successfully")
                try {
                    acqSurveyData=it.data.SAcqSiteAcquisition?.get(parentIndex)
                    adapter.setData(it.data.SAcqSiteAcquisition?.get(parentIndex)?.SAcqAcquitionSurvey?.get(0))
                } catch (e: java.lang.Exception) {
                    AppLogger.log("SiteAgreemnets AqcSurvey Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("SiteAgreemnets AqcSurvey size :${it.data.SAcqSiteAcquisition?.get(parentIndex)?.SAcqAcquitionSurvey?.size}")
            } else if (it != null) {
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment Something went wrong")

            }
        }


        viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
            viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
        }
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

    override fun viewInsidePremisesClicked(position: Int, data: SAcqInsidePremise) {
        val bm = InsidePremisesViewDialouge(R.layout.inside_premises_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editInsidePremisesClicked(position: Int, data: SAcqInsidePremise) {
        val bm = InsidePremisesEditDialouge(data,acqSurveyData,
            object : InsidePremisesEditDialouge.AcqInsidePremisesUpdateListener {
                override fun updatedData() {
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }

            },position)
        bm.show(childFragmentManager,"sdg")
    }

    override fun viewOutsidePremisesClicked(position: Int, data: SAcqOutsidePremise) {
        val bm = OutsidePremisesViewDialouge(R.layout.outside_premises_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editOutsidePremisesClicked(position: Int, data: SAcqOutsidePremise) {
        val bm = OutSidePremisesEditDialouge(data,acqSurveyData,
            object : OutSidePremisesEditDialouge.AcqOutsidePremisesUpdateListener {
                override fun updatedData() {
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }

            },position)
        bm.show(childFragmentManager,"sdg")
    }

    override fun viewPropertyOwnerClicked(position: Int, data: SAcqPropertyOwnerDetail) {
        val bm = PropertyOwnerViewDialouge(R.layout.acq_property_owner_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editPropertyOwnerClicked(position: Int, data: SAcqPropertyOwnerDetail) {
        val bm = PropertyOwnerEditDialouge(data,acqSurveyData,
            object : PropertyOwnerEditDialouge.AcqPropertyOwnerUpdateListener {
                override fun updatedData() {
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }

            },position)
        bm.show(childFragmentManager,"sdg")
    }
    override fun addAttachment() {
        val bm = AttachmentCommonDialogBottomSheet("SAcqAcquitionSurvey",acqSurveyData?.SAcqAcquitionSurvey?.get(0)?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun updateItemClicked(data: AcquisitionSurveyData) {
        showLoader()
        val dataModel = UpdateSiteAcquiAllData()
        val tempList:ArrayList<AcquisitionSurveyData> =ArrayList()
        tempList.clear()
        tempList.add(data)
        dataModel.SAcqAcquitionSurvey=tempList
        dataModel.id=acqSurveyData?.id
        viewmodel.updateSiteAcq(dataModel)
        if (viewmodel.updateSiteAcqDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && (it.data.status.SAcqPowerConnectionFeasibility==200 || it.data.status.SAcqFeasibilityDetail==200 || it.data.status.SAcqPropertyDetail==200)) {
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
            }
            else if (it != null) {
                AppLogger.log("SiteAgreemnets Fragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SiteAgreemnets Fragment Something went wrong")

            }
        }
    }


}
