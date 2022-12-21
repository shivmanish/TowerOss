package com.smarthub.baseapplication.ui.utilites.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentUtilitesNocBinding
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.utilites.BatteryBankDetailsActivity
import com.smarthub.baseapplication.ui.utilites.SMPSDetailsActivity
import com.smarthub.baseapplication.ui.utilites.adapter.UtilitesNocDataAdapter
import com.smarthub.baseapplication.ui.utilites.adapter.UtilitesNocDataAdapterListener
import com.smarthub.baseapplication.ui.utilites.UtilitiesNocActivity
import com.smarthub.baseapplication.ui.utilites.UtilitiesNocViewModel

class UtilitiesNocMainTabFragment : Fragment(), UtilitesNocDataAdapterListener {
    private val ARG_PARAM1 = "param1"
    lateinit var nocBinding: FragmentUtilitesNocBinding
    lateinit var viewmodel: UtilitiesNocViewModel
    lateinit var nocDataAdapter: UtilitesNocDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        nocBinding = FragmentUtilitesNocBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[UtilitiesNocViewModel::class.java]
        initializeFragment()


//        findNavController().navigate(UtilitiesNocMainTabFragmentDirections.actionUtilitiesNocMainTabFragmentToAC1UtilitesFrag())
        return nocBinding.root
    }

    private fun initializeFragment() {
        nocBinding.utilitesNocList.layoutManager = LinearLayoutManager(requireContext())
        nocDataAdapter = UtilitesNocDataAdapter(this@UtilitiesNocMainTabFragment)
        nocBinding.utilitesNocList.adapter = nocDataAdapter
        nocBinding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }
        viewmodel.fetchData()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            UtilitiesNocMainTabFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun clickedItem(position:Int) {
        if(position==0) {
            requireActivity().startActivity(
                Intent(
                    requireContext(),
                    SMPSDetailsActivity::class.java
                )
            )

        }
        else if(position==1) {
            requireActivity().startActivity(
                Intent(
                    requireContext(),
                    BatteryBankDetailsActivity::class.java
                )
            )

        }
        else if(position==2) { //Change for AC
            requireActivity().startActivity(
                Intent(
                    requireContext(),
                    BatteryBankDetailsActivity::class.java
                )
            )

        }
        else if(position==3) { //Change for DG
            requireActivity().startActivity(
                Intent(
                    requireContext(),
                    BatteryBankDetailsActivity::class.java
                )
            )

        }
        else if(position==4) { // Change for Fire
            requireActivity().startActivity(
                Intent(
                    requireContext(),
                    BatteryBankDetailsActivity::class.java
                )
            )

        }
        else if(position==5) { // Change for SPD
            requireActivity().startActivity(
                Intent(
                    requireContext(),
                    BatteryBankDetailsActivity::class.java
                )
            )

        }else{
            requireActivity().startActivity(
                Intent(
                    requireContext(),
                    UtilitiesNocActivity::class.java
                )
            )

        }
    }

}