package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TowerFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.FilterdTwrData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilPODetail
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraTowerModel
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TowerInfoFragment(var towerdata:FilterdTwrData?): Fragment(), TowerInfoListAdapter.TowerInfoListListener {
    var viewmodel: HomeViewModel?=null
    lateinit var binding : TowerFragmentBinding
    lateinit var adapter:TowerInfoListAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = TowerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=TowerInfoListAdapter(requireContext(),this@TowerInfoFragment,towerdata?.TowerDetails?.TowerAndCivilInfraTower?.get(0))
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
                AppLogger.log("TowerCivil Fragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.TowerAndCivilInfra?.get(towerdata?.index!!)?.TowerAndCivilInfraTower?.get(0))
                }catch (e:java.lang.Exception){
                    AppLogger.log("TowerCivil Fragment error : ${e.localizedMessage}")
                    Toast.makeText(context,"TowerCivil Fragment error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
                AppLogger.log("size :${it.data.TowerAndCivilInfra?.size}")
            }else if (it!=null) {
                Toast.makeText(requireContext(),"TowerCivil Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("TowerCivil Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("TowerCivil Fragment Something went wrong")
                Toast.makeText(requireContext(),"TowerCivil Fragment Something went wrong", Toast.LENGTH_SHORT).show()
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
    }

    override fun viewPoClicked(position: Int,data: TwrCivilPODetail) {
        var bm = TowerPoViewAdapter(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(position: Int) {
        var bm = TowerConsumableEditAdapter(R.layout.tower_consumable_edit_dialouge)
        bm.show(childFragmentManager, "category")
    }

    override fun viewConsumableClicked(position: Int,data: TwrCivilConsumableMaterial) {
        var bm = TowerConsumableViewAdapter(R.layout.tower_consumable_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editOffsetClicked(position: Int) {
        var bm = TowerOffsetEditAdapter(R.layout.tower_offset_edit_dialouge)
        bm.show(childFragmentManager, "category")
    }

    override fun viewOffsetClicked(position: Int) {
        var bm = TowerOffsetViewAdapter(R.layout.tower_offset_view_dialouge)
        bm.show(childFragmentManager, "category")
    }


}
