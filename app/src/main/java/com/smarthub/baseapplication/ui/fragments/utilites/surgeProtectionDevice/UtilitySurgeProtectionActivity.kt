package com.smarthub.baseapplication.ui.fragments.utilites.surgeProtectionDevice

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivitySpdDetailsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAC
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentSurgeProtectionDevice
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class UtilitySurgeProtectionActivity : BaseActivity(),UtilitySurgFragAdapter.UtilitySurgListListener {
    lateinit var binding:ActivitySpdDetailsBinding
    lateinit var adapter: UtilitySurgFragAdapter
    var surgAllData: ArrayList<UtilityEquipmentSurgeProtectionDevice>?=null
    lateinit var viewmodel: HomeViewModel
    var count:String="0"
    companion object{
        var utilityData: UtilityEquipmentAllData?=null
        var id:String?="448"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpdDetailsBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        binding.back.setOnClickListener {
            onBackPressed()
        }
        if (utilityData!=null){
            count=utilityData?.UtilityEquipmentSurgeProtectionDevice?.size.toString()
            adapter= UtilitySurgFragAdapter(this, utilityData?.UtilityEquipmentSurgeProtectionDevice)
            binding.listItem.adapter=adapter
        }
        if (viewmodel.utilityEquipResponse?.hasActiveObservers() == true){
            viewmodel.utilityEquipResponse?.removeObservers(this)
        }
        viewmodel.utilityEquipResponse?.observe(this) {
            if (it!=null && it.status == Resource.Status.LOADING){
                AppLogger.log("UtilitySurgeProtectionActivity data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("UtilitySurgeProtectionActivity card Data fetched successfully")
                try {
                    surgAllData=it.data.UtilityEquipment?.get(0)?.UtilityEquipmentSurgeProtectionDevice
                    count=it.data.UtilityEquipment?.get(0)?.UtilityEquipmentSurgeProtectionDevice?.size.toString()
                    adapter.setData(it.data.UtilityEquipment?.get(0)?.UtilityEquipmentSurgeProtectionDevice)
                }catch (e:java.lang.Exception){
                    AppLogger.log("UtilitySurgeProtectionActivity error : ${e.localizedMessage}")
                }
                AppLogger.log("UtilitySurgeProtectionActivity size :${it.data.UtilityEquipment?.get(0)?.UtilityEquipmentSmps?.size}")
            }else if (it!=null) {
                AppLogger.log("UtilitySurgeProtectionActivity error :${it.message}, it.data : ${it.data}, in line 71")
            }
            else {
                AppLogger.log("UtilitySurgeProtectionActivity Something went wrong")
            }
        }
        binding.Count.text=count

        binding.addItemsLayout.setOnClickListener {
            val bm = AddNewUtilitySurgDialouge(
                utilityData,
                object : AddNewUtilitySurgDialouge.AddUtilitySurgeDataListener {
                    override fun addNewData(){
                        showLoader()
                        viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                    }
                })
            bm.show(supportFragmentManager,"sdg")
        }
    }

    override fun updateACData(updatedData: UtilityEquipmentAC) {
        
    }


}