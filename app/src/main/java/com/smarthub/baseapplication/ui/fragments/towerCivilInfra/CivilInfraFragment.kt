package com.smarthub.baseapplication.ui.fragments.towerCivilInfra

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.CivilInfraFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraEarthingModel
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraEquipmentModel
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraPoleModel
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraTowerModel
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.ServiceRequestAddNew
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.addCardBottomSheet.EarthingAddNew
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.addCardBottomSheet.EquipmentRoomAddNew
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.addCardBottomSheet.PoleAddNew
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.addCardBottomSheet.TowerAddNew
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class CivilInfraFragment(var id:String) : BaseFragment(),CivilInfraAdapter.CivilInfraAdapterListner {
    var viewmodel: HomeViewModel?=null
    lateinit var binding : CivilInfraFragmentBinding
    lateinit var adapter: CivilInfraAdapter
    var isDataLoaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CivilInfraFragmentBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }

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
                    adapter.setData(it.data.TowerAndCivilInfra)
                }catch (e:java.lang.Exception){
                    AppLogger.log("TowerCivil Fragment error : ${e.localizedMessage}")
                    Toast.makeText(context,"TowerCivil Fragment error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
                AppLogger.log("Tower civil Infra data size :${it.data.TowerAndCivilInfra}")
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
            viewmodel?.TowerAndCivilRequestAll(id)
        }
    }

    fun initViews(){
        adapter=CivilInfraAdapter(requireContext(),this@CivilInfraFragment,id)
        binding.powerConnList.adapter = adapter
    }

    override fun onDestroy() {
        if (viewmodel?.TowerCivilInfraModelResponse?.hasActiveObservers() == true){
            viewmodel?.TowerCivilInfraModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (viewmodel!=null && !isDataLoaded){
            binding.swipeLayout.isRefreshing=true
            viewmodel?.TowerAndCivilRequestAll(id)
        }
        AppLogger.log("onViewPageSelected TowerAndCivil")
    }

    override fun clickedTowerItem(id:String,data:ArrayList<NewTowerCivilAllData>?) {
        TwrInfraDetails.Id=id
        TwrInfraDetails.TowerModelData=data
        requireActivity().startActivity(Intent(requireContext(), TwrInfraDetails::class.java))
    }

    override fun clickedPoleItem(id:String,data:ArrayList<NewTowerCivilAllData>?) {
        PoleFragment.Id=id
        PoleFragment.TowerModelData=data
        requireActivity().startActivity(Intent(requireContext(), PoleFragment::class.java))
    }

    override fun clickedEquipmentRoomItem(id:String,data:ArrayList<NewTowerCivilAllData>?) {
//        TowerEquipmentFragemnt.EquipmentModelData = data
//        TowerEquipmentFragemnt.Id=id
//        requireActivity().startActivity(Intent(requireContext(), TowerEquipmentFragemnt::class.java))
    }

    override fun clickedEarthingItem(id:String,data:ArrayList<NewTowerCivilAllData>?) {
//        TowerEarthingFragment.Id=id
//        TowerEarthingFragment.EarthingModelData=data as ArrayList<TowerAndCivilInfraEarthingModel>
//        requireActivity().startActivity(Intent(requireContext(), TowerEarthingFragment::class.java))
    }

    override fun addTower() {
        val bmSheet = TowerAddNew(R.layout.tower_civil_add_tower)
        bmSheet.show(childFragmentManager,"category")
    }

    override fun addPole() {
        val bmSheet = PoleAddNew(R.layout.tower_civil_add_tower)
        bmSheet.show(childFragmentManager,"category")
    }

    override fun addEquipmentRoom() {
        val bmSheet = EquipmentRoomAddNew(R.layout.tower_civil_add_equipment_room)
        bmSheet.show(childFragmentManager,"category")
    }

    override fun addEarthing() {
        val bmSheet = EarthingAddNew(R.layout.tower_civil_add_earthing)
        bmSheet.show(childFragmentManager,"category")
    }
}