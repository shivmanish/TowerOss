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
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.tower.adapter.TowerInfoListAdapter
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
        adapter= TowerInfoListAdapter(requireContext(),this@TowerInfoFragment,towerdata)
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
                    fullData=it.data.TowerAndCivilInfra?.get(0)
                    towerdata=it.data.TowerAndCivilInfra?.get(0)?.TowerAndCivilInfraTower?.get(childIndex)
                    adapter.setData(it.data.TowerAndCivilInfra?.get(0)?.TowerAndCivilInfraTower?.get(childIndex))
                }catch (e:java.lang.Exception){
                    AppLogger.log("TowerInfoFragment error : ${e.localizedMessage}")
                    Toast.makeText(context,"TowerInfoFragment error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
                AppLogger.log("size :${it.data.TowerAndCivilInfra?.size}")
            }else if (it!=null) {
                Toast.makeText(requireContext(),"TowerInfoFragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("TowerInfoFragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("TowerInfoFragment Something went wrong")
                Toast.makeText(requireContext(),"TowerInfoFragment Something went wrong", Toast.LENGTH_SHORT).show()
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
        val bm = TowerPoEditAdapter(R.layout.tower_po_edit_dialouge)
        bm.show(childFragmentManager, "category")
    }

    override fun viewPoClicked(position: Int,data: TwrCivilPODetail) {
        val bm = TowerPoViewAdapter(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(position: Int) {
        val bm = TowerConsumableEditAdapter(R.layout.tower_consumable_edit_dialouge)
        bm.show(childFragmentManager, "category")
    }

    override fun viewConsumableClicked(position: Int,data: TwrCivilConsumableMaterial) {
        val bm = TowerConsumableViewAdapter(R.layout.tower_consumable_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewMaintenenceClicked(position: Int, data: PreventiveMaintenance) {
        val bm= TowerMaintenenceViewAdapter(R.layout.tower_maintenence_view_dialouge,data)
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
