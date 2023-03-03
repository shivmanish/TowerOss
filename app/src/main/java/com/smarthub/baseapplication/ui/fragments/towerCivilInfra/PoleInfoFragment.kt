package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TwrcivilPoleInfoFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.FilterdTwrData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.PreventiveMaintenance
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilPODetail
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraPoleModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PoleInfoFragment (var towerdata: FilterdTwrData): BaseFragment(), PoleInfoFragAdapter.PoleInfoListListener {
    var viewmodel: HomeViewModel?=null
    lateinit var binding : TwrcivilPoleInfoFragmentBinding
    lateinit var adapter:PoleInfoFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = TwrcivilPoleInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=PoleInfoFragAdapter(requireContext(),this@PoleInfoFragment,towerdata?.TowerDetails?.TowerAndCivilInfraPole?.get(0))
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
                AppLogger.log("TowerCivil Fragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.TowerAndCivilInfra?.get(towerdata?.index!!)?.TowerAndCivilInfraPole?.get(0))
                }catch (e:java.lang.Exception){
                    AppLogger.log("TowerCivil Fragment error : ${e.localizedMessage}")

                }
                AppLogger.log("size :${it.data.TowerAndCivilInfra?.size}")
            }else if (it!=null) {
                AppLogger.log("TowerCivil Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("TowerCivil Fragment Something went wrong")
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            showLoader()
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
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
    //
    override fun EditInstallationAcceptence() {
        val bottomSheetDialogFragment = TowerInstallationEditAdapter(R.layout.tower_installation_edit_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun EditTowerItem() {
        val bottomSheetDialogFragment = TowerInfoEditDialougeAdapter(R.layout.tower_info_edit_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }
    override fun editPoClicked(position: Int) {
        var bm = TowerPoEditAdapter(R.layout.tower_po_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewPoClicked(position: Int,data:TwrCivilPODetail) {
        var bm = TowerPoViewAdapter(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewMaintenenceClicked(position: Int, data: PreventiveMaintenance) {
        val bm= TowerMaintenenceViewAdapter(R.layout.tower_maintenence_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(position: Int) {
        var bm = TowerConsumableEditAdapter(R.layout.tower_consumable_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewConsumableClicked(position: Int,data:TwrCivilConsumableMaterial) {
        var bm = TowerConsumableViewAdapter(R.layout.tower_consumable_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }




}
