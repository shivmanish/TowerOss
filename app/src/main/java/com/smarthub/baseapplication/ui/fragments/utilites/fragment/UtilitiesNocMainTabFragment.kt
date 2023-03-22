package com.smarthub.baseapplication.ui.fragments.utilites.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentUtilitesNocBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newUtilityEquipment.UtilityEquipmentAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.utilites.SMPSDetailsActivity
import com.smarthub.baseapplication.ui.fragments.utilites.ac.ACDetailsActivity
import com.smarthub.baseapplication.ui.fragments.utilites.ac.AddNewACDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.adapter.UtilitesNocDataAdapter
import com.smarthub.baseapplication.ui.fragments.utilites.adapter.UtilitesNocDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.utilites.addNewDialouge.AddNewSMPSDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.AddNewBatteryBankDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.batteryBank.BatteryBankDetailsActivity
import com.smarthub.baseapplication.ui.fragments.utilites.dg.AddNewDGDialouge
import com.smarthub.baseapplication.ui.fragments.utilites.dg.DGDetailsActivity
import com.smarthub.baseapplication.ui.utilites.*
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class UtilitiesNocMainTabFragment(var id:String) : BaseFragment(), UtilitesNocDataAdapterListener {
    lateinit var binding: FragmentUtilitesNocBinding
    var viewmodel: HomeViewModel?=null
    lateinit var adapter: UtilitesNocDataAdapter
    var isDataLoaded = false
    var utilitydatalist: ArrayList<UtilityEquipmentAllData>? = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUtilitesNocBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listItem.layoutManager = LinearLayoutManager(requireContext())
        adapter = UtilitesNocDataAdapter(this@UtilitiesNocMainTabFragment)
        binding.listItem.adapter = adapter
        
        binding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }

        if (viewmodel?.utilityEquipResponse?.hasActiveObservers() == true){
            viewmodel?.utilityEquipResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.utilityEquipResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                AppLogger.log("UtilityEquip Fragment data loading in progress ")
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                binding.swipeLayout.isRefreshing=false
                hideLoader()
                AppLogger.log("UtilityEquip Fragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.UtilityEquipment)
                    utilitydatalist?.clear()
                    it.data.UtilityEquipment?.let { it1 -> utilitydatalist?.addAll(it1) }
                }catch (e:java.lang.Exception){
                    AppLogger.log("UtilityEquip Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("UtilityEquip size :${it.data.UtilityEquipment?.size}")
                isDataLoaded = true
            }else if (it!=null) {
                AppLogger.log("UtilityEquip Fragment error :${it.message}, it.data : ${it.data}, in line 71")
            }
            else {
                AppLogger.log("UtilityEquip Fragment Something went wrong")
            }
        }

       binding.swipeLayout.setOnRefreshListener {
           binding.swipeLayout.isRefreshing=false
           showLoader()
           viewmodel?. utilityRequestAll(AppController.getInstance().siteid)
       }
    }



    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (viewmodel!=null || !isDataLoaded){
            adapter.addLoading()
            viewmodel?. utilityRequestAll(AppController.getInstance().siteid)
        }
        AppLogger.log("onViewPageSelected UtilityEquip")
    }



    override fun SMPSItemClicked(data: UtilityEquipmentAllData?) {
        SMPSDetailsActivity.utilitySmpsData=data
        requireActivity().startActivity(Intent(requireContext(), SMPSDetailsActivity::class.java))
    }

    override fun BateeryItemClicked(data: UtilityEquipmentAllData?) {
        BatteryBankDetailsActivity.utilityData=data
        requireActivity().startActivity(Intent(requireContext(), BatteryBankDetailsActivity::class.java))
    }

    override fun DGItemClicked(data: UtilityEquipmentAllData?) {
        DGDetailsActivity.utilityData=data
        requireActivity().startActivity(Intent(requireContext(), DGDetailsActivity::class.java))
    }

    override fun ACItemClicked(data: UtilityEquipmentAllData?) {
        ACDetailsActivity.utilityData=data
        requireActivity().startActivity(Intent(requireContext(), ACDetailsActivity::class.java))
    }

    override fun FireExtinguisherItemClicked(data: UtilityEquipmentAllData?) {
        requireActivity().startActivity(Intent(requireContext(), FireExtinguisherDetailsActivity::class.java))
    }

    override fun SuregeProtectionDeviceItemClicked(data: UtilityEquipmentAllData?) {
        requireActivity().startActivity(Intent(requireContext(), SurgeProtectionDeviceDetailsActivity::class.java))
    }

    override fun PowerDistributionBoxItemClicked(data: UtilityEquipmentAllData?) {
    }

    override fun CableItemClicked(data: UtilityEquipmentAllData?) {
    }

    override fun addNewSMPS() {
        val bm = AddNewSMPSDialouge(utilitydatalist,
            object : AddNewSMPSDialouge.AddSMPSDataListener {
                override fun addNewData(){
                    showLoader()
                    viewmodel?.utilityRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun addNewBatterryBank() {
        val bm = AddNewBatteryBankDialouge(utilitydatalist,
            object : AddNewBatteryBankDialouge.AddBatteryBankDataListener {
                override fun addNewData(){
                    showLoader()
                    viewmodel?.utilityRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun addNewDG() {
        val bm = AddNewDGDialouge(utilitydatalist,
            object : AddNewDGDialouge.AddDGDataListener {
                override fun addNewData(){
                    showLoader()
                    viewmodel?.utilityRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

    override fun addNewAC() {
        val bm = AddNewACDialouge(utilitydatalist,
            object : AddNewACDialouge.AddACDataListener {
                override fun addNewData(){
                    showLoader()
                    viewmodel?.utilityRequestAll(AppController.getInstance().siteid)
                }
            })
        bm.show(childFragmentManager,"sdg")
    }

}