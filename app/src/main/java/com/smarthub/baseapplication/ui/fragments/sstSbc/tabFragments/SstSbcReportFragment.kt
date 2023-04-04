package com.smarthub.baseapplication.ui.fragments.sstSbc.tabFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.SiteAcqTeamNonSwitLayoutBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcTestReport
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.sstSbc.adapter.SstSbcReportFragAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SstSbcReportFragment(var sstSbcData:SstSbcAllData?, var parentIndex:Int): BaseFragment(),SstSbcReportFragAdapter.SstSbcReportListListener{
    lateinit var viewmodel: HomeViewModel
    lateinit var binding : SiteAcqTeamNonSwitLayoutBinding
    lateinit var adapter:SstSbcReportFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = SiteAcqTeamNonSwitLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= SstSbcReportFragAdapter(this@SstSbcReportFragment,this@SstSbcReportFragment,sstSbcData)
        binding.listItem.adapter = adapter

        if (viewmodel.sstSbcModelResponse?.hasActiveObservers() == true) {
            viewmodel.sstSbcModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.sstSbcModelResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SstSbcTeamFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("SstSbcTeamFragment card Data fetched successfully")
                hideLoader()
                try {
                    sstSbcData=it.data.SstSbc?.get(parentIndex)
                    adapter.setData(it.data.SstSbc?.get(parentIndex)?.SstSbcTestReport?.get(0))
                } catch (e: java.lang.Exception) {
                    AppLogger.log("SstSbcTeamFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("SiteAgreemnets size :${it.data.SstSbc?.size}")
            } else if (it != null) {
                AppLogger.log("SstSbcTeamFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SstSbcTeamFragment Something went wrong")

            }
        }



        viewmodel.fetchSstSbcModelRequest(AppController.getInstance().siteid)
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
        val bm = AttachmentCommonDialogBottomSheet("SstSbcTeam",sstSbcData?.SstSbcTeam?.get(0)?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel.fetchSstSbcModelRequest(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun updateTeamClicked(data: SstSbcTestReport) {
        showLoader()
        val dataModel = SstSbcAllData()
        dataModel.SstSbcTestReport= arrayListOf(data)
        dataModel.id=sstSbcData?.id
        viewmodel.updateSstSbc(dataModel)
        if (viewmodel.updateSstSbcDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateSstSbcDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.updateSstSbcDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("SstSbcTeamFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("SstSbcTeamFragment card Data fetched successfully")
                viewmodel.fetchSstSbcModelRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("SstSbcTeamFragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("SstSbcTeamFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("SstSbcTeamFragment Something went wrong")

            }
        }
    }




}
