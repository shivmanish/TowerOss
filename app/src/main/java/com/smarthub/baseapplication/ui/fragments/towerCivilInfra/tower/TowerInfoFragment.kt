package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.*
import com.smarthub.baseapplication.ui.fragments.AttachmentCommonDialogBottomSheet
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower.adapter.TowerInfoListAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower.dialouge.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TowerInfoFragment(var towerdata:TowerAndCivilInfraTower?,var fullData: NewTowerCivilAllData?,var childIndex:Int): BaseFragment(),
    TowerInfoListAdapter.TowerInfoListListener {
    var viewmodel: HomeViewModel?=null
    lateinit var binding : TowerFragmentBinding
    lateinit var adapter: TowerInfoListAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = TowerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= TowerInfoListAdapter(this@TowerInfoFragment,this@TowerInfoFragment,towerdata)
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
                AppLogger.log("TowerInfoFragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.TowerAndCivilInfra?.get(0)?.TowerAndCivilInfraTower?.get(childIndex))
                    fullData=it.data.TowerAndCivilInfra?.get(0)
                    towerdata=it.data.TowerAndCivilInfra?.get(0)?.TowerAndCivilInfraTower?.get(childIndex)
                }catch (e:java.lang.Exception){
                    AppLogger.log("TowerInfoFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("size :${it.data.TowerAndCivilInfra?.size}")
            }else if (it!=null) {
//                Toast.makeText(requireContext(),"TowerInfoFragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("TowerInfoFragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("TowerInfoFragment Something went wrong")
//                Toast.makeText(requireContext(),"TowerInfoFragment Something went wrong", Toast.LENGTH_SHORT).show()
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
//        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun addAttachment() {
        val bm = AttachmentCommonDialogBottomSheet("TowerAndCivilInfraTower",towerdata?.id.toString(),
            object : AttachmentCommonDialogBottomSheet.AddAttachmentListner {
                override fun attachmentAdded(){
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun updateTowerData(updatedData: TowerAndCivilInfraTower) {
        showLoader()
        val dataModel = NewTowerCivilAllData()
        dataModel.TowerAndCivilInfraTower= arrayListOf(updatedData)
        if (fullData!=null) 
            dataModel.id=fullData?.id
        viewmodel?.updateTwrCivilInfra(dataModel)
        if (viewmodel?.updateTwrCivilInfraDataResponse?.hasActiveObservers() == true) {
            viewmodel?.updateTwrCivilInfraDataResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.updateTwrCivilInfraDataResponse?.observe(viewLifecycleOwner) {
            if (it != null && it.status == Resource.Status.LOADING) {
                AppLogger.log("TowerInfoFragment data updating in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS) {
                AppLogger.log("TowerInfoFragment card Data Updated successfully")
                viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
//                Toast.makeText(context,"Data Updated successfully", Toast.LENGTH_SHORT).show()
            }
            else if (it?.data != null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("TowerInfoFragment Something went wrong in updating data")
//                Toast.makeText(context,"Something went wrong in update data . Try again", Toast.LENGTH_SHORT).show()
            }
            else if (it != null) {
                AppLogger.log("TowerInfoFragment error :${it.message}, data : ${it.data}")
            } else {
                AppLogger.log("TowerInfoFragment Something went wrong in updating data")

            }
        }
    }

    override fun editPoClicked(data:TwrCivilPODetail) {
        val bm = TowerPoEditAdapter(data,towerdata?.id,fullData?.id,
            object : TowerPoEditAdapter.TowerPoUpdateListener {
                override fun updatedData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }

    override fun viewPoClicked(data: TwrCivilPODetail) {
        val bm = TowerPoViewAdapter(data)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(data: TwrCivilConsumableMaterial) {
        val bm = TowerConsumableEditAdapter(data,towerdata?.id,fullData?.id,
            object : TowerConsumableEditAdapter.TowerConsumUpdateListner {
                override fun updatedData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }

    override fun viewConsumableClicked(data: TwrCivilConsumableMaterial) {
        val bm = TowerConsumableViewAdapter(data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewMaintenenceClicked(data: PreventiveMaintenance) {
        val bm= TowerMaintenenceViewAdapter(data)
        bm.show(childFragmentManager, "category")
    }

    override fun editMaintenenceClicked(data: PreventiveMaintenance) {
        val bm = TowerMaintenanceEditDialouge(data,towerdata?.id,fullData?.id,
            object : TowerMaintenanceEditDialouge.TowerMaintenanceUpdateListener {
                override fun updatedData() {
                    viewmodel?.TowerAndCivilRequestAll(AppController.getInstance().siteid)
                }

            })
        bm.show(childFragmentManager, "category")
    }

    override fun editOffsetClicked(position: Int) {
        val bm = TowerOffsetEditAdapter(R.layout.tower_offset_edit_dialouge)
        bm.show(childFragmentManager, "category")
    }

    override fun viewOffsetClicked(position: Int) {
        val bm = TowerOffsetViewAdapter(R.layout.tower_offset_view_dialouge)
        bm.show(childFragmentManager, "category")
    }


}
