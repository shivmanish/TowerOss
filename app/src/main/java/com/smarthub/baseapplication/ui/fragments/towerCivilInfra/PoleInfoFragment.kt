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
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraPoleModel
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PoleInfoFragment (var towerdata: TowerAndCivilInfraPoleModel?, var id:String?, var index:Int): Fragment(), PoleInfoFragAdapter.PoleInfoListListener {
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
        adapter=PoleInfoFragAdapter(requireContext(),this@PoleInfoFragment,towerdata)
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
                    adapter.setData(it.data.item!![0].TowerAndCivilInfra.get(0).TowerAndCivilInfraPoleModel.get(index))
                }catch (e:java.lang.Exception){
                    AppLogger.log("TowerCivil Fragment error : ${e.localizedMessage}")
                    Toast.makeText(context,"TowerCivil Fragment error :${e.localizedMessage}",
                        Toast.LENGTH_LONG).show()
                }
                AppLogger.log("size :${it.data.item?.size}")
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
            viewmodel?.TowerAndCivilRequestAll(id!!)
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

    override fun viewPoClicked(position: Int) {
        var bm = TowerPoViewAdapter(R.layout.tower_po_view_dialouge)
        bm.show(childFragmentManager, "category")
    }

    override fun editConsumableClicked(position: Int) {
        var bm = TowerConsumableEditAdapter(R.layout.tower_consumable_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewConsumableClicked(position: Int) {
        var bm = TowerConsumableViewAdapter(R.layout.tower_consumable_view_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun editOffsetClicked(position: Int) {
        var bm = TowerOffsetEditAdapter(R.layout.tower_offset_edit_dialouge)
        bm.show(childFragmentManager, "category")
        Toast.makeText(requireContext() , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewOffsetClicked(position: Int) {
        var bm = TowerOffsetViewAdapter(R.layout.tower_offset_view_dialouge)
        bm.show(childFragmentManager, "category")
    }


}
