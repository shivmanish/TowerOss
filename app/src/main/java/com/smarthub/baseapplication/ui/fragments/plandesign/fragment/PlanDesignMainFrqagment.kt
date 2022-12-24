package com.smarthub.baseapplication.ui.fragments.plandesign.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentServiceRequestBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.plandesign.PowerDesignDetailsActivity
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.PlanDesignAdapter
import com.smarthub.baseapplication.ui.fragments.plandesign.adapter.PlanDesignAdapterListener
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PlanDesignMainFrqagment : Fragment(), PlanDesignAdapterListener {

    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    lateinit var viewmodel: HomeViewModel
    lateinit var customerDataAdapter: PlanDesignAdapter
    lateinit var customerBinding: FragmentServiceRequestBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        customerBinding = FragmentServiceRequestBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        initializeFragment()
        return customerBinding.root
    }

    private fun initializeFragment() {
        customerBinding.customerList.layoutManager = LinearLayoutManager(requireContext())
        customerDataAdapter = PlanDesignAdapter(this@PlanDesignMainFrqagment, ArrayList())
        customerBinding.customerList.adapter = customerDataAdapter
        customerDataAdapter.updateData("anything")
        customerDataAdapter.updateData("anything")
        customerBinding.addMore.setOnClickListener{
            customerDataAdapter.updateData("anything")
        }

        customerBinding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }
        if (viewmodel.getServiceRequest?.hasActiveObservers() == true)
            viewmodel.getServiceRequest?.removeObservers(viewLifecycleOwner)
        viewmodel.getServiceRequest?.observe(viewLifecycleOwner){
            if (it.status == Resource.Status.SUCCESS){
                if (it.data!=null){
                    mapUIData(it.data)
                }
                AppLogger.log("Site request data fetched successfully")
            }else{
                AppLogger.log("Something went wrong")
            }
        }
        viewmodel.fetchServiceRequestData("97")
    }

    private fun mapUIData(data : ServiceRequestAllData){
        AppLogger.log("data fetched")

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            PlanDesignMainFrqagment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun clickedItem() {
        requireActivity().startActivity(Intent(requireContext(), PowerDesignDetailsActivity::class.java))

    }
}