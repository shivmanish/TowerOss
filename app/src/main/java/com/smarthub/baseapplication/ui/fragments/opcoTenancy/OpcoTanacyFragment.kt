package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoTenencyFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel


class OpcoTanacyFragment : BaseFragment(), CustomerDataAdapterListener {
    private val ARG_PARAM1 = "param1"
    lateinit var binding: OpcoTenencyFragmentBinding
    lateinit var viewmodel: HomeViewModel
    lateinit var customerDataAdapter: CustomerDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = OpcoTenencyFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.customerList.layoutManager = LinearLayoutManager(requireContext())
        customerDataAdapter = CustomerDataAdapter(this@OpcoTanacyFragment)
        binding.customerList.adapter = customerDataAdapter

        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }

        if (viewmodel.opcoCardListResponse?.hasActiveObservers() == true)
            viewmodel.opcoCardListResponse?.removeObservers(viewLifecycleOwner)
        viewmodel.opcoCardListResponse?.observe(viewLifecycleOwner) {
            binding.swipeLayout.isRefreshing = false
            if (it!=null && it.status == Resource.Status.LOADING){
                showLoader()
                return@observe
            }
            if (it!=null && it.status == Resource.Status.SUCCESS){
                hideLoader()
                AppLogger.log("OpcoTenencyFragment card Data fetched successfully")
                customerDataAdapter.setData(it.data!!)
            }else if (it!=null) {
                Toast.makeText(requireContext(),"OpcoTenencyFragment error :${it.message}", Toast.LENGTH_SHORT).show()
            }else{
                AppLogger.log("OpcoTenencyFragment Something went wrong")
                Toast.makeText(requireContext(),"OpcoTenencyFragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroy() {
        if (viewmodel.opcoCardListResponse?.hasActiveObservers() == true)
            viewmodel.opcoCardListResponse?.removeObservers(viewLifecycleOwner)
        super.onDestroy()
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