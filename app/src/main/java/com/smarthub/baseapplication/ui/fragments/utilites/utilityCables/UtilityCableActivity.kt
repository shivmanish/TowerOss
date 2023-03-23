package com.smarthub.baseapplication.ui.fragments.utilites.utilityCables

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivitySpdDetailsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityCableDetail
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.utilityUpdate.UpdateUtilityEquipmentAllData
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class UtilityCableActivity : BaseActivity(),UtilityCableActivityAdapter.UtilityCableListListener {
    lateinit var binding:ActivitySpdDetailsBinding
    lateinit var adapter: UtilityCableActivityAdapter
    var cableAllData: ArrayList<UtilityCableDetail>?=null
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
            count=utilityData?.CableDetail?.size.toString()
            adapter= UtilityCableActivityAdapter(this,this, utilityData?.CableDetail)
            binding.listItem.adapter=adapter
        }
        if (viewmodel.utilityEquipResponse?.hasActiveObservers() == true){
            viewmodel.utilityEquipResponse?.removeObservers(this)
        }
        viewmodel.utilityEquipResponse?.observe(this) {
            if (it!=null && it.status == Resource.Status.LOADING){
                AppLogger.log("UtilityCableActivity data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("UtilityCableActivity card Data fetched successfully")
                try {
                    cableAllData=it.data.UtilityEquipment?.get(0)?.CableDetail
                    utilityData=it.data.UtilityEquipment?.get(0)
                    count=it.data.UtilityEquipment?.get(0)?.CableDetail?.size.toString()
                    adapter.setData(it.data.UtilityEquipment?.get(0)?.CableDetail)
                }catch (e:java.lang.Exception){
                    AppLogger.log("UtilityCableActivity error : ${e.localizedMessage}")
                }
                AppLogger.log("UtilityCableActivity size :${it.data.UtilityEquipment?.get(0)?.UtilityEquipmentSmps?.size}")
            }else if (it!=null) {
                AppLogger.log("UtilityCableActivity error :${it.message}, it.data : ${it.data}, in line 71")
            }
            else {
                AppLogger.log("UtilityCableActivity Something went wrong")
            }
        }
        binding.Count.text=count
        binding.title.text="Cables"

        binding.addItemsLayout.setOnClickListener {
            val bm = AddNewUtilityCableDialouge(
                utilityData,
                object : AddNewUtilityCableDialouge.AddUtilityCableDataListener {
                    override fun addNewData(){
                        showLoader()
                        viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                    }
                })
            bm.show(supportFragmentManager,"sdg")
        }
    }
    

    override fun updateUtilityCableData(updatedData: UtilityCableDetail) {
        showLoader()
        val utilityAllDataModel = UpdateUtilityEquipmentAllData()
        utilityAllDataModel.CableDetail= arrayListOf(updatedData)
        if (utilityData!=null)
            utilityAllDataModel.id= utilityData?.id
        viewmodel.updateUtilityEquip(utilityAllDataModel)
        if (viewmodel.updateUtilityDataResponse?.hasActiveObservers() == true) {
            viewmodel.updateUtilityDataResponse?.removeObservers(this)
        }
        viewmodel.updateUtilityDataResponse?.observe(this) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("UtilityCableActivity data Updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.status.CableDetail==200 ) {
                AppLogger.log("UtilityCableActivity card Data Updated successfully")
                viewmodel.utilityRequestAll(AppController.getInstance().siteid)
                Toast.makeText(this,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                Toast.makeText(this,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
                AppLogger.log("UtilityCableActivity Something went wrong in updating data")
            }
            else if (it != null) {
                AppLogger.log("UtilityCableActivity error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("UtilityCableActivity Something went wrong in updating data")

            }
        }
    }


}