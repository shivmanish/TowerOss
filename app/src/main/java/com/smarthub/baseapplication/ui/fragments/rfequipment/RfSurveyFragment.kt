package com.smarthub.baseapplication.ui.fragments.rfequipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.databinding.PowerFuelCommonTabFragBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.Attachments
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.rfequipment.adapter.RfSurveyFragAdapter
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey1
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class RfSurveyFragment(var rfSurveyData: RfSurvey?, var parentIndex:Int): BaseFragment(),RfSurveyFragAdapter.RfSurveyClickListener{
    var viewmodel: HomeViewModel?=null
    lateinit var binding : PowerFuelCommonTabFragBinding
    lateinit var adapter:RfSurveyFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = PowerFuelCommonTabFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= RfSurveyFragAdapter(this@RfSurveyFragment,this@RfSurveyFragment,rfSurveyData?.RfSurvey1)
        binding.listItem.adapter = adapter
        if (viewmodel?.rfMainResponse?.hasActiveObservers() == true){
            viewmodel?.rfMainResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.rfMainResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
//                adapter.addLoading()
                AppLogger.log("RfSurveyFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                binding.swipeLayout.isRefreshing=false
                AppLogger.log("RfSurveyFragment card Data fetched successfully")
                try {
                    AppLogger.log("all data of RF Survey : ====> ${Gson().toJson(it.data.RfSurvey?.get(parentIndex))}")
                    rfSurveyData=it.data.RfSurvey?.get(parentIndex)
                    adapter.setData(it.data.RfSurvey?.get(parentIndex)?.RfSurvey1)
                    hideLoader()
                }catch (e:java.lang.Exception){
                    AppLogger.log("RfSurveyFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("RfSurvey size :${it.data.RfSurvey?.size}")
            }else if (it!=null) {
                AppLogger.log("RfSurveyFragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("RfSurveyFragment Something went wrong")
            }
        }

        binding.addItemsLayout.setOnClickListener{
            val bm = AddNewRfSurveyDialouge(rfSurveyData,
                object : AddNewRfSurveyDialouge.AddRfSurveyDataListener {
                    override fun addNewData() {
                        viewmodel?.fetchRfRequest(AppController.getInstance().siteid)
                    }

                })
            bm.show(childFragmentManager,"sdg")
        }

        binding.swipeLayout.setOnRefreshListener {
//            binding.swipeLayout.isRefreshing=false
//            adapter.addLoading()
            viewmodel?.fetchRfRequest(AppController.getInstance().siteid)
        }
        viewmodel?.fetchRfRequest(AppController.getInstance().siteid)
    }

    override fun onDestroy() {
        if (viewmodel?.TowerCivilInfraModelResponse?.hasActiveObservers() == true){
            viewmodel?.TowerCivilInfraModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }


    override fun updateRfSurvey(updatedData: RfSurvey1) {
        showLoader()
        val dataModel = RfSurvey()
        dataModel.RfSurvey1= arrayListOf(updatedData)
        dataModel.id=rfSurveyData?.id
        viewmodel?.updateRfSurvey(dataModel)
        if (viewmodel?.updateRfSurveyDataResponse?.hasActiveObservers() == true) {
            viewmodel?.updateRfSurveyDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.updateRfSurveyDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("RfSurveyFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.RfSurvey==200) {
                AppLogger.log("RfSurveyFragment card Data fetched successfully")
                viewmodel?.fetchRfRequest(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully",Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("RfSurveyFragment Something went wrong")
            }
            else if (it != null) {
                AppLogger.log("RfSurveyFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("RfSurveyFragment Something went wrong")

            }
        }
    }

    override fun addAttachment(id: String, moduel: String) {
        val bm = AttachmentCommonDialogBottomSheet(moduel,id,
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun attachmentItemClicked(data:Attachments) {

    }


}
