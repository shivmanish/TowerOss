package com.smarthub.baseapplication.ui.fragments.powerAndFuel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PowerConnectionFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newPowerFuel.NewPowerFuelAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnDataAdapter
import com.smarthub.baseapplication.ui.fragments.powerAndFuel.adapter.PowerConnectionListListener
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerConnection (var id:String): BaseFragment(), PowerConnectionListListener {

    lateinit var binding: PowerConnectionFragmentBinding
    var viewmodel: HomeViewModel?=null
    lateinit var adapter: PowerConnDataAdapter
    var isDataLoaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PowerConnectionFragmentBinding.inflate(inflater)
        viewmodel = ViewModelProvider(this).get(HomeViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }
        adapter=PowerConnDataAdapter(requireContext(),this)
        binding.powerConnList.adapter=adapter

        binding.addNew.setOnClickListener {
//            val bmSheet = AddNewPowerFuelDialouge(R.layout.power_fuel_addnew_dialouge)
//            bmSheet.show(childFragmentManager,"category")
        }

        if (viewmodel?.powerAndFuelResponse?.hasActiveObservers() == true){
            viewmodel?.powerAndFuelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.powerAndFuelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                adapter.addLoading()
                AppLogger.log("PowerFuel Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("PowerFuel Fragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.PowerAndFuel)
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
            adapter.addLoading()
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

    override fun clickedItem(data: NewPowerFuelAllData,parentIndex:Int) {
        PowerConnectionDetailsActivity.powerFuelData=data
        PowerConnectionDetailsActivity.parentIndex=parentIndex
        requireActivity().startActivity(Intent(requireContext(), PowerConnectionDetailsActivity::class.java))
    }
}