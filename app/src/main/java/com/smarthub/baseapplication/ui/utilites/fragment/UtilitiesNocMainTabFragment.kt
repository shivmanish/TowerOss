package com.smarthub.baseapplication.ui.utilites.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentUtilitesNocBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtililitiesEquipAllDadaItem
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.UtilitieSmp
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.utilites.BatteryBankDetailsActivity
import com.smarthub.baseapplication.ui.utilites.SMPSDetailsActivity
import com.smarthub.baseapplication.ui.utilites.adapter.UtilitesNocDataAdapter
import com.smarthub.baseapplication.ui.utilites.adapter.UtilitesNocDataAdapterListener
import com.smarthub.baseapplication.ui.utilites.UtilitiesNocActivity
import com.smarthub.baseapplication.ui.utilites.UtilitiesNocViewModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class UtilitiesNocMainTabFragment(var id:String) : BaseFragment(), UtilitesNocDataAdapterListener {
    lateinit var nocBinding: FragmentUtilitesNocBinding
    var viewmodel: HomeViewModel?=null
    lateinit var adapter: UtilitesNocDataAdapter
    var isDataLoaded = false
    var utilitydatalist: ArrayList<UtililitiesEquipAllDadaItem>? = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        nocBinding = FragmentUtilitesNocBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        initializeFragment()
        return nocBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nocBinding.addMore.setOnClickListener{
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
                AppLogger.log("UtilityEquip Fragment card Data fetched successfully")
                try {
                    adapter.setData(it.data.item!![0].utilities)
                    utilitydatalist?.clear()
                    utilitydatalist?.addAll(it.data.item!![0].utilities)
                }catch (e:java.lang.Exception){
                    AppLogger.log("UtilityEquip Fragment error : ${e.localizedMessage}")
                    Toast.makeText(context,"UtilityEquip Fragment error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
                }
                AppLogger.log("UtilityEquip size :${it.data.item!![0].utilities.size}")
                isDataLoaded = true
            }else if (it!=null) {
                Toast.makeText(requireContext(),"UtilityEquip Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("UtilityEquip Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("UtilityEquip Fragment Something went wrong")
                Toast.makeText(requireContext(),"UtilityEquip Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initializeFragment() {
        nocBinding.utilitesNocList.layoutManager = LinearLayoutManager(requireContext())
        adapter = UtilitesNocDataAdapter(this@UtilitiesNocMainTabFragment)
        nocBinding.utilitesNocList.adapter = adapter
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (viewmodel!=null || !isDataLoaded){
            viewmodel?.utilityRequestAll(id)
        }
        AppLogger.log("onViewPageSelected UtilityEquip")
    }


    override fun clickedItem(position:Int) {
        if(position==0 && isDataLoaded) { // SMPS
            SMPSDetailsActivity.id=id
            SMPSDetailsActivity.utilitySmpsData=utilitydatalist?.get(0)?.UtilitieSmps as ArrayList<UtilitieSmp>
            requireActivity().startActivity(Intent(requireContext(), SMPSDetailsActivity::class.java))
        }
        else if(position==1) { //Battery bank
            requireActivity().startActivity(Intent(requireContext(), BatteryBankDetailsActivity::class.java))
        }
        else if(position==2) { // DG
            requireActivity().startActivity(Intent(requireContext(), BatteryBankDetailsActivity::class.java))
        }
        else if(position==3) { //AC
            requireActivity().startActivity(Intent(requireContext(), BatteryBankDetailsActivity::class.java))
        }
        else if(position==4) { //Fire
            requireActivity().startActivity(Intent(requireContext(), BatteryBankDetailsActivity::class.java))
        }
        else if(position==5) { //SPD
            requireActivity().startActivity(Intent(requireContext(), BatteryBankDetailsActivity::class.java))

        }else{
            requireActivity().startActivity(Intent(requireContext(), UtilitiesNocActivity::class.java))

        }
    }

}