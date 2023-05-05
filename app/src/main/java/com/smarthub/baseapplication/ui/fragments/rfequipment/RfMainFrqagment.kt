package com.smarthub.baseapplication.ui.fragments.rfequipment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfSurveyFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newsstSbc.SstSbcAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.rfequipment.pojo.RfSurvey
import com.smarthub.baseapplication.ui.fragments.sstSbc.AddNewSstSbcDialouge
import com.smarthub.baseapplication.ui.fragments.sstSbc.SstSbcTabActivity
import com.smarthub.baseapplication.ui.fragments.sstSbc.adapter.SstSbcAdapterListener
import com.smarthub.baseapplication.ui.fragments.sstSbc.adapter.SstSbcMainAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class RfMainFrqagment(var id:String) : BaseFragment(), RfAdapterListener {

    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: RfMainAdapter
    lateinit var binding: RfSurveyFragmentBinding
    var isDataLoaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = RfSurveyFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customerList.layoutManager = LinearLayoutManager(requireContext())
        adapter = RfMainAdapter(this@RfMainFrqagment, id)
        binding.customerList.adapter = adapter

        if (viewmodel.rfMainResponse?.hasActiveObservers() == true){
            viewmodel.rfMainResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.rfMainResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            hideLoader()
            if (it?.data != null && it.status == Resource.Status.SUCCESS && it.data.RfSurvey!=null){
               AppLogger.log("SstSbcMainFrqagment card Data fetched successfully")
                hideLoader()
               adapter.setData(it.data.RfSurvey!!)
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
            viewmodel.fetchRfRequest(id)
        }
        
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }
        binding.addNew.setOnClickListener {
            val bmSheet = AddRfDialouge(id,(
                    object : AddRfDialouge.AddSstSbcDataListener {
                        override fun addNewData(){
                            showLoader()
                            viewmodel.fetchRfRequest(AppController.getInstance().siteid)
                        }
                    }))
            bmSheet.show(childFragmentManager,"category")
        }
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (!isDataLoaded){
            adapter.addLoading()
            showLoader()
            viewmodel.fetchRfRequest(id)
        }
        AppLogger.log("onViewPageSelected PlanAndDesign")
    }

    override fun onDestroy() {
        if (viewmodel.PlanDesignModelResponse?.hasActiveObservers() == true){
            viewmodel.PlanDesignModelResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroy()
    }

    

    override fun clickedItem(data : RfSurvey?, index:Int) {
        Toast.makeText(requireContext(),"this is called ",Toast.LENGTH_SHORT).show()
        RfTabActivity.rfSurvey=data
        RfTabActivity.parentIndex=index
        requireActivity().startActivity(Intent(requireContext(), RfTabActivity::class.java))

    }

}