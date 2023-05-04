package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerEarthingInfoFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.*
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing.adapters.EarthingInfoFragmentAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing.dialouge.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TowerEarthingInfoFragment(var earthingData: TowerAndCivilInfraEarthing?,var fullData:NewTowerCivilAllData?,var childIndex:Int): BaseFragment(),
    EarthingInfoFragmentAdapter.TowerEarthingListListener {
    lateinit var binding : TowerEarthingInfoFragmentBinding
    var viewmodel: HomeViewModel?=null
    lateinit var adapter: EarthingInfoFragmentAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = TowerEarthingInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=
            EarthingInfoFragmentAdapter(this@TowerEarthingInfoFragment,this@TowerEarthingInfoFragment,earthingData)
        binding.listItem.adapter = adapter

        if (viewmodel?.TowerCivilInfraModelResponse?.hasActiveObservers() == true){
            viewmodel?.TowerCivilInfraModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.TowerCivilInfraModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                binding.swipeLayout.isRefreshing=false
                hideLoader()
                AppLogger.log("TowerEarthingInfoFragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.TowerAndCivilInfra?.get(0)?.TowerAndCivilInfraEarthing?.get(childIndex))
                    earthingData=it.data.TowerAndCivilInfra?.get(0)?.TowerAndCivilInfraEarthing?.get(childIndex)
                    fullData=it.data.TowerAndCivilInfra?.get(0)
                }catch (e:java.lang.Exception){
                    AppLogger.log("TowerEarthingInfoFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("TowerEarthingInfoFragment size :${it.data.TowerAndCivilInfra?.size}")
            }else if (it!=null) {
                AppLogger.log("TowerEarthingInfoFragment   error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("TowerEarthingInfoFragment Something went wrong")
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
        }
    }

    override fun onDestroy() {
        if (viewmodel?.TowerCivilInfraModelResponse?.hasActiveObservers() == true){
            viewmodel?.TowerCivilInfraModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun attachmentItemClicked() {
    }
    override fun addAttachment() {
        val bm = AttachmentCommonDialogBottomSheet("TowerAndCivilInfraEarthing",earthingData?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }
    override fun updateEarthingData(data: TowerAndCivilInfraEarthing) {
        showLoader()
        val dataModel = NewTowerCivilAllData()
        dataModel.TowerAndCivilInfraEarthing= arrayListOf(data)
        if (fullData!=null)
            dataModel.id=fullData?.id
        viewmodel?.updateTwrCivilInfra(dataModel)
        if (viewmodel?.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel?.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("TowerEarthingInfoFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("TowerEarthingInfoFragment card Data Updated successfully")
                viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("TowerEarthingInfoFragment Something went wrong in updating data")
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
            }
            else if (it != null) {
                AppLogger.log("TowerEarthingInfoFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("TowerEarthingInfoFragment Something went wrong in updating data")

            }
        }
    }

    override fun editPoClicked(data:TwrCivilPODetail) {
        val bm = EarthingPOEditDialouge(data,earthingData?.id,fullData?.id,
            object : EarthingPOEditDialouge.EarthingPoUpdateListner {
                override fun updateData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }

    override fun viewPoClicked(data:TwrCivilPODetail) {
        val bm = EarthingPoViewDialouge(data)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(data:TwrCivilConsumableMaterial) {
        val bm = EarthingConsumableEditDialouge(data,earthingData?.id,fullData?.id,
            object : EarthingConsumableEditDialouge.EarthConsumUpdateListner {
                override fun updateData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }

    override fun viewConsumableClicked(data:TwrCivilConsumableMaterial) {
        val bm = EarthingConsumableViewDialouge(data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewEarthingDetails(data: TwrCivilInfraEarthingDetail) {
        val bm = EarthingDetailsViewDialouge(data)
        bm.show(childFragmentManager, "category")
    }
    override fun editEarthingDetails(data: TwrCivilInfraEarthingDetail) {
        val bm = EarthingDetailEditDialouge(data,earthingData?.id,fullData?.id,
            object : EarthingDetailEditDialouge.EarthingUpdateListner {
                override fun updateData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }

    override fun editMaintenenceClicked(data: PreventiveMaintenance) {
        val bm= EarthingMaintenanceEditDialouge(data,earthingData?.id,fullData?.id,
            object : EarthingMaintenanceEditDialouge.EarthMaintenanceUpdateListener {
                override fun updatedData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }
    override fun viewMaintenenceClicked(data: PreventiveMaintenance) {
        val bm= TowerMaintenenceViewAdapter(data)
        bm.show(childFragmentManager, "category")
    }

}