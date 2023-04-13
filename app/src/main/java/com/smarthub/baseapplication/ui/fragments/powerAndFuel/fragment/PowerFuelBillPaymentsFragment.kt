package com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.PowerFuelCommonTabFragBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelEBPayments
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerFuelBillPaymentsAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.AddNewPowerFuelBillsPayDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerFuelBillPaymentsFragment(var powerFuelData:NewPowerFuelAllData?,var parentIndex:Int): BaseFragment(),PowerFuelBillPaymentsAdapter.PowerBillPaymentsClickListener{
    var viewmodel: HomeViewModel?=null
    lateinit var binding : PowerFuelCommonTabFragBinding
    lateinit var adapter:PowerFuelBillPaymentsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = PowerFuelCommonTabFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= PowerFuelBillPaymentsAdapter(this@PowerFuelBillPaymentsFragment,this@PowerFuelBillPaymentsFragment,powerFuelData?.PowerAndFuelEBPayment)
        binding.listItem.adapter = adapter

        if (viewmodel?.powerAndFuelResponse?.hasActiveObservers() == true){
            viewmodel?.powerAndFuelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.powerAndFuelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
//                adapter.addLoading()
                AppLogger.log("PowerFuel Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                binding.swipeLayout.isRefreshing=false
                hideLoader()
                AppLogger.log("PowerFuel Fragment card Data fetched successfully")
                try {
                    powerFuelData=it.data.PowerAndFuel?.get(parentIndex)
                    adapter.setData(it.data.PowerAndFuel?.get(parentIndex)?.PowerAndFuelEBPayment)
                }catch (e:java.lang.Exception){
                    AppLogger.log("PowerFuel Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("PowerFuel size :${it.data.PowerAndFuel?.size}")
            }else if (it!=null) {
                AppLogger.log("PowerFuel Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("PowerFuel Fragment Something went wrong")
            }
        }

        binding.addItemsLayout.setOnClickListener{
            val bm = AddNewPowerFuelBillsPayDialouge(powerFuelData,
                object : AddNewPowerFuelBillsPayDialouge.AddBillPaymentsListener {
                    override fun addNewData() {
                        viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
                    }

                })
            bm.show(childFragmentManager,"sdg")
        }
        
        binding.swipeLayout.setOnRefreshListener {
//            binding.swipeLayout.isRefreshing=false
//            adapter.addLoading()
            viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
        }
    }

    override fun onDestroy() {
        if (viewmodel?.TowerCivilInfraModelResponse?.hasActiveObservers() == true){
            viewmodel?.TowerCivilInfraModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun updateBillPayments(data: PowerFuelEBPayments) {
        showLoader()
        val dataModel = NewPowerFuelAllData()
        dataModel.PowerAndFuelEBPayment= arrayListOf(data)
        if (powerFuelData!=null)
            dataModel.id=powerFuelData?.id
        viewmodel?.updatePowerFuel(dataModel)
        if (viewmodel?.updatePowerFuelDataResponse?.hasActiveObservers() == true) {
            viewmodel?.updatePowerFuelDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.updatePowerFuelDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("PowerFuelBillPaymentsFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status?.PowerAndFuelEBPayment==200) {
                AppLogger.log("PowerFuelBillPaymentsFragment card Data Updated successfully")
                viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("PowerFuelBillPaymentsFragment Something went wrong in updating data")
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
            }
            else if (it != null) {
                AppLogger.log("PowerFuelBillPaymentsFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("PowerFuelBillPaymentsFragment Something went wrong in updating data")

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

    override fun attachmentItemClicked() {
    }


}
