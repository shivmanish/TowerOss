package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.pole

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TwrcivilPoleInfoFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.*
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.TowerInstallationEditAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.TowerMaintenenceViewAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.pole.adapters.PoleInfoFragAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.pole.dialouges.PoleConsumableEditAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.pole.dialouges.PoleMaintenanceEditDialouge
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.pole.dialouges.PolePoEditAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower.dialouge.TowerConsumableViewAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower.dialouge.TowerPoViewAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PoleInfoFragment (var poleData: TowerAndCivilInfraPole?,var fullData: NewTowerCivilAllData?,var childIndex:Int): BaseFragment(),
    PoleInfoFragAdapter.PoleInfoListListener {
    var viewmodel: HomeViewModel?=null
    lateinit var binding : TwrcivilPoleInfoFragmentBinding
    lateinit var adapter: PoleInfoFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = TwrcivilPoleInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= PoleInfoFragAdapter(this@PoleInfoFragment,this@PoleInfoFragment,poleData)
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
                AppLogger.log("PoleInfoFragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.TowerAndCivilInfra?.get(0)?.TowerAndCivilInfraPole?.get(childIndex))
                    fullData=it.data.TowerAndCivilInfra?.get(0)
                    poleData=it.data.TowerAndCivilInfra?.get(0)?.TowerAndCivilInfraPole?.get(childIndex)
                }catch (e:java.lang.Exception){
                    AppLogger.log("PoleInfoFragment error : ${e.localizedMessage}")

                }
                AppLogger.log("size :${it.data.TowerAndCivilInfra?.size}")
            }else if (it!=null) {
                AppLogger.log("PoleInfoFragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("PoleInfoFragment Something went wrong")
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
        val bm = AttachmentCommonDialogBottomSheet("TowerAndCivilInfraPole",poleData?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }
    override fun EditInstallationAcceptence() {
        val bottomSheetDialogFragment = TowerInstallationEditAdapter(R.layout.tower_installation_edit_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun updatePoleData(data: TowerAndCivilInfraPole) {
        showLoader()
        val dataModel = NewTowerCivilAllData()
        dataModel.TowerAndCivilInfraPole= arrayListOf(data)
        if (fullData!=null)
            dataModel.id=fullData?.id
        viewmodel?.updateTwrCivilInfra(dataModel)
        if (viewmodel?.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel?.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("PoleInfoFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("PoleInfoFragment card Data Updated successfully")
                viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("PoleInfoFragment Something went wrong in updating data")
                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
            }
            else if (it != null) {
                AppLogger.log("PoleInfoFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("PoleInfoFragment Something went wrong in updating data")

            }
        }
    }
    
    override fun editPoClicked(data:TwrCivilPODetail) {
        val bm = PolePoEditAdapter(data,poleData?.id,fullData?.id,
            object : PolePoEditAdapter.PolePoUpdateListener {
                override fun updatedData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }

    override fun viewPoClicked(data:TwrCivilPODetail) {
        val bm = TowerPoViewAdapter(data)
        bm.show(childFragmentManager, "category")
    }

    override fun editMaintenenceClicked(data: PreventiveMaintenance) {
        val bm = PoleMaintenanceEditDialouge(data,poleData?.id,fullData?.id,
            object : PoleMaintenanceEditDialouge.PoleMaintenanceUpdateListener {
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

    override fun editConsumableClicked(data:TwrCivilConsumableMaterial) {
        val bm = PoleConsumableEditAdapter(data,poleData?.id,fullData?.id,
            object : PoleConsumableEditAdapter.PoleConsumUpdateListner {
                override fun updatedData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }

    override fun viewConsumableClicked(data:TwrCivilConsumableMaterial) {
        val bm = TowerConsumableViewAdapter(data)
        bm.show(childFragmentManager, "category")
    }




}
