package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerEarthingInfoFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.*
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TowerEarthingInfoFragment(var earthingData: FilterdTwrData?): Fragment(), EarthingInfoFragmentAdapter.TowerEarthingListListener {
    lateinit var binding : TowerEarthingInfoFragmentBinding
    var viewmodel: HomeViewModel?=null
    lateinit var adapter:EarthingInfoFragmentAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = TowerEarthingInfoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=EarthingInfoFragmentAdapter(requireContext(),this@TowerEarthingInfoFragment,earthingData?.TowerDetails?.TowerAndCivilInfraEarthing?.get(0))
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
                AppLogger.log("TwrCivil Fragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.TowerAndCivilInfra?.get(earthingData?.index!!)?.TowerAndCivilInfraEarthing?.get(0))
                }catch (e:java.lang.Exception){
                    AppLogger.log("TwrCivil earth Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("size :${it.data.TowerAndCivilInfra?.size}")
            }else if (it!=null) {
                AppLogger.log("TwrCivil earth  error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("TwrCivil earth  Fragment Something went wrong")
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
        Toast.makeText(requireContext(),"Item Clicked", Toast.LENGTH_SHORT).show()
    }
    //
    override fun EditInstallationAcceptence() {
        val bottomSheetDialogFragment = EarthingInstallationEditAdapter(R.layout.earthing_installation_edit_dilaouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun EditEarthingItem() {

        val bottomSheetDialogFragment = EarthingInfoDialougeAdapter(R.layout.tower_earthing_info_dialouge)
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun editPoClicked(position: Int) {
        val bm = EditEarthingPOTableBottomSheet(R.layout.earthing_po_item_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewPoClicked(position: Int,data:TwrCivilPODetail) {
        val bm = EarthingPoTableViewDialougeAdapter(R.layout.earthing_po_item_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(position: Int) {
        val bm = EarthingConsumableEditDialougeAdapter(R.layout.earthing_consumable_table_edit_dialouge)
        bm.show(childFragmentManager, "category")
    }

    override fun viewConsumableClicked(position: Int, data:TwrCivilConsumableMaterial) {
        val bm = EarthingConsumableTableViewDialougeAdapter(R.layout.earthing_consumable_table_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewEarthingDetails(position: Int, data: TwrCivilInfraEarthingDetail) {
        val bm = EarthingDetailsViewDialouge(R.layout.earthing_details_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewMaintenenceClicked(position: Int, data: PreventiveMaintenance) {
        val bm= TowerMaintenenceViewAdapter(data)
        bm.show(childFragmentManager, "category")
    }


}