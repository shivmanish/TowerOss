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
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.NewSiteAcquiAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqPODetail
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SiteAcqAgreement
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.siteAcqUpdate.UpdateSiteAcquiAllData
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.adapters.AgreementFragAdapter
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.ImageViewBottomSheet
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge.AgreementPoEditDialouge
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.dialouge.AgreementPoViewDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class AgreementFragment(var acqAgree:NewSiteAcquiAllData?, var parentIndex:Int): BaseFragment(),AgreementFragAdapter.AgreementListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : PowerConnectionFragBinding
    lateinit var adapter:AgreementFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = PowerConnectionFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= AgreementFragAdapter(this@AgreementFragment,this@AgreementFragment,acqAgree)
        binding.listItem.adapter = adapter

        if (viewmodel.siteAgreementModel?.hasActiveObservers() == true) {
            viewmodel.siteAgreementModel?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.siteAgreementModel?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets AqcSurvey Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                hideLoader()
                AppLogger.log("SiteAgreemnets AqcSurvey Data fetched successfully")
                try {
                    acqAgree=it.data.SAcqSiteAcquisition?.get(parentIndex)
                    adapter.setData(it.data.SAcqSiteAcquisition?.get(parentIndex)?.SAcqAgreement?.get(0))
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

    override fun attachmentItemClicked(item: Attachments) {
        val bm = ImageViewBottomSheet(item)
        bm.show(childFragmentManager,"sdg")
    }


    override fun viewPoItemClicked(position: Int, data: SAcqPODetail) {
        val bm = AgreementPoViewDialouge(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager,"sdg")
    }

    override fun editPoItemClicked(position: Int, data: SAcqPODetail) {
        val bm = AgreementPoEditDialouge(data,acqAgree,
            object : AgreementPoEditDialouge.AcqPoUpdateListener {

                override fun updatedData() {
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }

            },position)
        bm.show(childFragmentManager,"sdg")
    }

    override fun addAttachment() {
        val bm = AttachmentCommonDialogBottomSheet("SAcqAgreement",acqAgree?.SAcqAgreement?.get(0)?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun updateAgreementDetailsClicked(data: SiteAcqAgreement) {
        showLoader()
        val dataModel = UpdateSiteAcquiAllData()
        val tempList:ArrayList<SiteAcqAgreement> =ArrayList()
        tempList.clear()
        tempList.add(data)
        dataModel.SAcqAgreement=tempList
        dataModel.id=acqAgree?.id
        viewmodel.updateSiteAcq(dataModel)
        if (viewmodel.updateSiteAcqDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSiteAcqDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSiteAcqDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SiteAgreemnets Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.SAcqAgreementDetail==200) {
                AppLogger.log("SiteAgreemnets Fragment card Data fetched successfully")
                viewmodel.fetchSiteAgreementModelRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
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
