package com.smarthub.baseapplication.ui.fragments.sstSbc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PlanDesignFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignDataItem
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.plandesign.PowerDesignDetailsActivity
import com.smarthub.baseapplication.ui.fragments.sstSbc.adapter.SstSbcAdapterListener
import com.smarthub.baseapplication.ui.fragments.sstSbc.adapter.SstSbcMainAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class SstSbcMainFrqagment(var id:String) : BaseFragment(), SstSbcAdapterListener {

    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: SstSbcMainAdapter
    lateinit var binding: PlanDesignFragmentBinding
    var isDataLoaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PlanDesignFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customerList.layoutManager = LinearLayoutManager(requireContext())
        adapter = SstSbcMainAdapter(this@SstSbcMainFrqagment, id)
        binding.customerList.adapter = adapter

        if (viewmodel.sstSbcModelResponse?.hasActiveObservers() == true){
            viewmodel.sstSbcModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.sstSbcModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.SstSbc!=null){
               AppLogger.log("SstSbcMainFrqagment card Data fetched successfully")
                hideLoader()
               adapter.setData(it.data.SstSbc!!)
               isDataLoaded = true
            }else if (it!=null) {
                Toast.makeText(requireContext(),"SstSbcMainFrqagment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("SstSbcMainFrqagment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("SstSbcMainFrqagment Something went wrong")
                Toast.makeText(requireContext(),"SstSbcMainFrqagment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
            adapter.addLoading()
            viewmodel.fetchSstSbcModelRequest(id)
        }
        
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }
        binding.addNew.setOnClickListener {
            val bmSheet = AddNewSstSbcDialouge((
                    object : AddNewSstSbcDialouge.AddSstSbcDataListener {
                        override fun addNewData(){
                            showLoader()
                            viewmodel.fetchSstSbcModelRequest(AppController.getInstance().siteid)
                        }
                    }))
            bmSheet.show(childFragmentManager,"category")
        }
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (!isDataLoaded){
            adapter.addLoading()
            viewmodel.fetchSstSbcModelRequest(id)
        }
        AppLogger.log("onViewPageSelected PlanAndDesign")
    }

    override fun onDestroy() {
        if (viewmodel.PlanDesignModelResponse?.hasActiveObservers() == true){
            viewmodel.PlanDesignModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    

    override fun clickedItem(data : SstSbcAllData?,index:Int) {
        SstSbcTabActivity.siteacquisition=data
        SstSbcTabActivity.parentIndex=index
        requireActivity().startActivity(Intent(requireContext(), SstSbcTabActivity::class.java))

    }

}