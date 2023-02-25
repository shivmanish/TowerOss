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
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.AddNewOpcoCardAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel


class OpcoTanacyFragment (var id : String): BaseFragment(), CustomerDataAdapterListener {
    lateinit var binding: OpcoTenencyFragmentBinding
    var viewmodel: HomeViewModel?=null
    var isDataLoaded = false
    lateinit var opcoTanancyFragAdapter: OpcoTanancyFragAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = OpcoTenencyFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.customerList.layoutManager = LinearLayoutManager(requireContext())
        opcoTanancyFragAdapter = OpcoTanancyFragAdapter(requireContext(),this@OpcoTanacyFragment)
        binding.customerList.adapter = opcoTanancyFragAdapter


        binding.addItems.setOnClickListener{
            val dalouge = AddNewOpcoCardAdapter(R.layout.add_new_card_opco_tenency)
            dalouge.show(childFragmentManager,"")
        }

        binding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }

        if (viewmodel?.opcoTenencyModelResponse?.hasActiveObservers() == true)
            viewmodel?.opcoTenencyModelResponse?.removeObservers(viewLifecycleOwner)
        viewmodel?.opcoTenencyModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                opcoTanancyFragAdapter.addLoading()
                return@observe
            }
            if (it!=null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("OpcoTenencyFragment card Data fetched successfully")
                isDataLoaded = true
                it.data?.Operator?.let { it1 -> opcoTanancyFragAdapter.setOpData(it1) }
            }else if (it!=null) {
                Toast.makeText(requireContext(),"OpcoTenencyFragment error :${it.message}", Toast.LENGTH_SHORT).show()
            }else{
                AppLogger.log("OpcoTenencyFragment Something went wrong")
                Toast.makeText(requireContext(),"OpcoTenencyFragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing = false
            opcoTanancyFragAdapter.addLoading()
            viewmodel?.opcoTenancyRequestAll(id)
        }

    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (viewmodel!=null && !isDataLoaded){
            opcoTanancyFragAdapter.addLoading()
            viewmodel?.opcoTenancyRequestAll(id)
        }
        AppLogger.log("onViewPageSelected Opco Tenanacy get data")
    }

    override fun onDestroy() {
        if (viewmodel?.opcoTenencyModelResponse?.hasActiveObservers() == true)
            viewmodel?.opcoTenencyModelResponse?.removeObservers(viewLifecycleOwner)
        super.onDestroy()
    }

    override fun clickedItem(data : OpcoTenencyAllData) {
        OpcoTenancyActivity.Opcodata=data
        requireActivity().startActivity(Intent(requireContext(), OpcoTenancyActivity::class.java))

//        requireActivity().startActivity(Intent(requireContext(), OpcoTenancyActivityNew::class.java))


    }
}