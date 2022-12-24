package com.smarthub.baseapplication.ui.fragments.services_request

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
import com.smarthub.baseapplication.databinding.FragmentServiceRequestBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesDataAdapter
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.CustomerDataAdapterListener
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesDataAdapterListener
import com.smarthub.baseapplication.ui.fragments.siteInfo.SiteInfoListAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ServicesRequestFrqagment : Fragment(), ServicesDataAdapterListener {

    private val ARG_PARAM1 = "param1"
    lateinit var viewmodel: HomeViewModel
    lateinit var customerDataAdapter: ServicesDataAdapter
    lateinit var customerBinding: FragmentServiceRequestBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        customerBinding = FragmentServiceRequestBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        initializeFragment()
        return customerBinding.root
    }

    private fun initializeFragment() {
        customerBinding.customerList.layoutManager = LinearLayoutManager(requireContext())
        customerDataAdapter = ServicesDataAdapter(this@ServicesRequestFrqagment)
        customerBinding.customerList.adapter = customerDataAdapter

        customerBinding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }
        if (viewmodel.serviceRequestAllData?.hasActiveObservers() == true){
            viewmodel.serviceRequestAllData?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.serviceRequestAllData?.observe(viewLifecycleOwner) {
            if (it!=null && it!!.data!=null){
                customerDataAdapter.setData(it.data!!)
                AppLogger.log("size :${it.data.size}")
            }else {
                Toast.makeText(requireContext(),"data not found",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun mapUIData(data : ServiceRequestAllData){
        AppLogger.log("data fetched")

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ServicesRequestFrqagment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun clickedItem(data : ServiceRequestAllDataItem) {
        ServicesRequestActivity.data = data
        requireActivity().startActivity(Intent(requireContext(), ServicesRequestActivity::class.java))

    }
}