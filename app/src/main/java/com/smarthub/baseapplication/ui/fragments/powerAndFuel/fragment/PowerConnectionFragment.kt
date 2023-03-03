package com.smarthub.baseapplication.ui.fragments.powerAndFuel.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionFragBinding
import com.smarthub.baseapplication.databinding.TowerFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.*
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.FilterdTwrData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.PreventiveMaintenance
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilConsumableMaterial
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.TwrCivilPODetail
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraTowerModel
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnecFragAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.PowerConsumableViewDialouge
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.PowerFuelAuthPaymentViewDialouge
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.PowerFuelPoViewDialouge
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerConnectionFragment(var powerConnData:NewPowerFuelAllData?): BaseFragment(),PowerConnecFragAdapter.PowerConnectionListListener{
    var viewmodel: HomeViewModel?=null
    lateinit var binding : PowerConnectionFragBinding
    lateinit var adapter:PowerConnecFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = PowerConnectionFragBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= PowerConnecFragAdapter(requireContext(),this@PowerConnectionFragment,powerConnData)
        binding.listItem.adapter = adapter

        if (viewmodel?.powerAndFuelResponse?.hasActiveObservers() == true){
            viewmodel?.powerAndFuelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.powerAndFuelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                showLoader()
                AppLogger.log("PowerFuel Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("PowerFuel Fragment card Data fetched successfully")
                try {
                    AppLogger.log("all data of power fuel : ====> ${Gson().toJson(it.data.PowerAndFuel?.get(0))}")
                    adapter.setData(it.data.PowerAndFuel?.get(0)?.PowerAndFuelEBConnection?.get(0))
                    hideLoader()
                }catch (e:java.lang.Exception){
                    AppLogger.log("PowerFuel Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("PowerFuel size :${it.data.PowerAndFuel?.size}")
            }else if (it!=null) {
                AppLogger.log("PowerFuel Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("PowerFuel Fragment Something went wrong")
            }
        }



        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
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

    override fun attachmentItemClicked() {
       AppLogger.log("Attachment clicked")
    }

    override fun viewPoClicked(position: Int, data: PowerFuelPODetail) {
        val bm = PowerFuelPoViewDialouge(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewConsumableClicked(position: Int, data: PowerConsumableMaterial) {
        val bm = PowerConsumableViewDialouge(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }

    override fun viewAuthorityPaymentClicked(position: Int, data: PowerFuelAuthorityPayments) {
        val bm = PowerFuelAuthPaymentViewDialouge(R.layout.tower_po_view_dialouge,data)
        bm.show(childFragmentManager, "category")
    }


}
