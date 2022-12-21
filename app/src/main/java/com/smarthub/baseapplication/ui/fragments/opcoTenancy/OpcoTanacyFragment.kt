package com.smarthub.baseapplication.ui.fragments.opcoTenancy

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
import com.smarthub.baseapplication.databinding.FragmentCustomerBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.siteInfo.SiteInfoListAdapter
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.CustomerDataAdapter
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.CustomerDataAdapterListener
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel


class OpcoTanacyFragment : BaseFragment(), CustomerDataAdapterListener {
    private val ARG_PARAM1 = "param1"
    lateinit var binding: FragmentCustomerBinding
    lateinit var viewmodel: HomeViewModel
    lateinit var customerDataAdapter: CustomerDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCustomerBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        initializeFragment()
        return binding.root
    }

    private fun initializeFragment() {
        binding.customerList.layoutManager = LinearLayoutManager(requireContext())
        customerDataAdapter = CustomerDataAdapter(this@OpcoTanacyFragment, ArrayList())
        binding.customerList.adapter = customerDataAdapter
        binding.addMore.setOnClickListener{
            customerDataAdapter.updateData("anything")
        }

        binding.addMore.setOnClickListener(){

            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }

//        if (viewmodel.siteInfoResponse?.hasActiveObservers() == true)
//            viewmodel.siteInfoResponse?.removeObservers(viewLifecycleOwner)
//        viewmodel.siteInfoResponse?.observe(viewLifecycleOwner) {
//            binding.swipeLayout.isRefreshing = false
//            if (it!=null && it.status == Resource.Status.LOADING){
//                showLoader()
//                return@observe
//            }
//            if (it!=null && it.status == Resource.Status.SUCCESS){
//                hideLoader()
//                AppLogger.log("SiteInfoNewFragment Site Data fetched successfully")
//
//            }else if (it!=null) {
//                Toast.makeText(requireContext(),"SiteInfoNewFragment error :${it.message}", Toast.LENGTH_SHORT).show()
//            }else{
//                AppLogger.log("SiteInfoNewFragment Something went wrong")
//                Toast.makeText(requireContext(),"SiteInfoNewFragment Something went wrong", Toast.LENGTH_SHORT).show()
//            }
//        }

//        viewmodel.fetchData()
//        viewmodel.customer_data.observe(requireActivity(), Observer {
//            // Data is get from server and ui work will be start from here
//            println("this is called data is $it")
//            var arraydata = ArrayList<String>()
//            arraydata.add(it)
//            customerDataAdapter.setData(arraydata)
//        })
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            OpcoTanacyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun clickedItem() {
        requireActivity().startActivity(Intent(requireContext(), OpcoTenancyActivity::class.java))

    }
}