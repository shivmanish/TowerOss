package com.smarthub.baseapplication.ui.fragments.services_request

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentServiceRequestBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesDataAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesDataAdapterListener
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class ServicesRequestFrqagment(var id : String) : BaseFragment(), ServicesDataAdapterListener {
    var viewmodel: HomeViewModel?=null
    var isDataLoaded = false
    lateinit var serviceFragAdapterAdapter: ServicesDataAdapter
    lateinit var customerBinding: FragmentServiceRequestBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        customerBinding = FragmentServiceRequestBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return customerBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customerBinding.customerList.layoutManager = LinearLayoutManager(requireContext())
        serviceFragAdapterAdapter = ServicesDataAdapter(this@ServicesRequestFrqagment,id)
        customerBinding.customerList.adapter = serviceFragAdapterAdapter

        customerBinding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }
        if (viewmodel?.serviceRequestModelResponse?.hasActiveObservers() == true){
            viewmodel?.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel?.serviceRequestModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.ServiceRequestMain!=null && it.data.ServiceRequestMain.isNotEmpty()){
                AppLogger.log("Service request Fragment card Data fetched successfully")
                serviceFragAdapterAdapter.setData(it.data.ServiceRequestMain as ArrayList<ServiceRequestAllDataItem>)
                AppLogger.log("size :${it.data.ServiceRequestMain.size}")
                isDataLoaded = true
            }
            else if (it!=null) {
                Toast.makeText(requireContext(),"Service request Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("Service request Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("Service Request Fragment Something went wrong")
                Toast.makeText(requireContext(),"Service Request Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        customerBinding.swipeLayout.setOnRefreshListener {
            customerBinding.swipeLayout.isRefreshing=false
            serviceFragAdapterAdapter.addLoading()
            viewmodel?.serviceRequestAll(id)
        }

        customerBinding.addItemsLayout.setOnClickListener {
            val bmSheet = ServiceRequestAddNew(R.layout.service_request_add_new_dialouge)
            bmSheet.show(childFragmentManager,"category")
        }
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (viewmodel!=null && !isDataLoaded){
            serviceFragAdapterAdapter.addLoading()
            viewmodel?.serviceRequestAll(id)
        }
        AppLogger.log("onViewPageSelected service request")
    }


    override fun onDestroy() {
        if (viewmodel?.serviceRequestModelResponse?.hasActiveObservers() == true){
            viewmodel?.serviceRequestModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    override fun clickedItem(data : ServiceRequestAllDataItem, Id : String) {
        ServicesRequestActivity.ServiceRequestdata = data
        ServicesRequestActivity.Id=Id
        requireActivity().startActivity(Intent(requireContext(), ServicesRequestActivity::class.java))
    }
}