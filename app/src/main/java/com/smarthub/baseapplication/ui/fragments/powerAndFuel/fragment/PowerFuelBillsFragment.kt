package com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.databinding.PowerFuelCommonTabFragBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.PowerFuelBills
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerFuelBillsAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.AddNewPowerFuelBillsDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerFuelBillsFragment(var powerFuelData:NewPowerFuelAllData?,var parentIndex:Int): BaseFragment(),PowerFuelBillsAdapter.PowerBillsClickListener{
    var viewmodel: HomeViewModel?=null
    lateinit var binding : PowerFuelCommonTabFragBinding
    lateinit var adapter:PowerFuelBillsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = PowerFuelCommonTabFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= PowerFuelBillsAdapter(this@PowerFuelBillsFragment,requireContext(),powerFuelData?.PowerAndFuelEBBil)
        binding.listItem.adapter = adapter
        if (viewmodel?.powerAndFuelResponse?.hasActiveObservers() == true){
            viewmodel?.powerAndFuelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.powerAndFuelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                adapter.addLoading()
                AppLogger.log("PowerFuelBillsFragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("PowerFuelBillsFragment card Data fetched successfully")
                try {
                    AppLogger.log("all data of power fuel : ====> ${Gson().toJson(it.data.PowerAndFuel?.get(0))}")
                    powerFuelData=it.data.PowerAndFuel?.get(parentIndex)
                    adapter.setData(it.data.PowerAndFuel?.get(parentIndex)?.PowerAndFuelEBBil)
                    hideLoader()
                }catch (e:java.lang.Exception){
                    AppLogger.log("PowerFuelBillsFragment error : ${e.localizedMessage}")
                }
                AppLogger.log("PowerFuel size :${it.data.PowerAndFuel?.size}")
            }else if (it!=null) {
                AppLogger.log("PowerFuelBillsFragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("PowerFuelBillsFragment Something went wrong")
            }
        }

        binding.addItemsLayout.setOnClickListener{
            val bm = AddNewPowerFuelBillsDialouge(powerFuelData,
                object : AddNewPowerFuelBillsDialouge.AddPowerFuelDataListener {
                    override fun addNewData() {
                        viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
                    }

                })
            bm.show(childFragmentManager,"sdg")
        }

        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
            adapter.addLoading()
            viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
        }
        viewmodel?.fetchPowerAndFuel(AppController.getInstance().siteid)
    }

    override fun onDestroy() {
        if (viewmodel?.TowerCivilInfraModelResponse?.hasActiveObservers() == true){
            viewmodel?.TowerCivilInfraModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun editModeCliked(data: PowerFuelBills, pos: Int) {

    }


}
