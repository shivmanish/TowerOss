package com.smarthub.baseapplication.ui.fragments.powerAndFuel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerAndFuelFragBinding
import com.smarthub.baseapplication.databinding.PowerConnectionFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.plandesign.dialouge.AddNewPlanDesignDialouge
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnDataAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnectionListListener
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerFuelTabAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.dialouge.AddNewPowerFuelDialouge
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.pojo.PowerAndFuel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerConnection (var id:String): BaseFragment(), PowerConnectionListListener {

//    lateinit var binding: PowerConnectionFragmentBinding
    lateinit var binding: PowerAndFuelFragBinding
    var viewmodel: HomeViewModel?=null
//    lateinit var adapter: PowerConnDataAdapter
    lateinit var adapter: PowerFuelTabAdapter
    var isDataLoaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PowerAndFuelFragBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }
        adapter=PowerFuelTabAdapter(childFragmentManager)
        binding.viewpager.adapter=adapter
        binding.tabs.setupWithViewPager(binding.viewpager)

//        binding.addNew.setOnClickListener {
//            val bmSheet = AddNewPowerFuelDialouge(R.layout.power_fuel_addnew_dialouge)
//            bmSheet.show(childFragmentManager,"category")
//        }

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
                    adapter.setPowerFuelData(it.data.PowerAndFuel?.get(0))
                    hideLoader()
                }catch (e:java.lang.Exception){
                    AppLogger.log("PowerFuel Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("PowerFuel size :${it.data.PowerAndFuel?.size}")
                isDataLoaded = true
            }else if (it!=null) {
                AppLogger.log("PowerFuel Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("PowerFuel Fragment Something went wrong")
            }
        }
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (viewmodel!=null || !isDataLoaded){
            showLoader()
            viewmodel?.fetchPowerAndFuel(id)
        }
        AppLogger.log("onViewPageSelected PowerAndFuel")
    }

    override fun onDestroy() {
        if (viewmodel?.powerAndFuelResponse?.hasActiveObservers() == true){
            viewmodel?.powerAndFuelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun clickedItem(data: PowerAndFuel) {
        val intent = Intent(requireContext(), PowerConnectionDetailsActivity::class.java)
        intent.putExtra("data", data)
        requireActivity().startActivity(intent)
    }
}