package com.smarthub.baseapplication.ui.fragments.noc

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.NocAndCompFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters.AddNewNocCmpDialouge
import com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters.CreateNocBottomSheet
import com.smarthub.baseapplication.ui.fragments.siteAcquisition.AddNewSiteAcqDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class NocFragment(var id : String): BaseFragment(), NocDataAdapterListener {
    lateinit var binding: NocAndCompFragmentBinding
    lateinit var viewmodel: HomeViewModel
    var isDataLoaded = false
    lateinit var nocDataAdapter: NocDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = NocAndCompFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.customerList.layoutManager = LinearLayoutManager(requireContext())
        nocDataAdapter = NocDataAdapter(requireContext(),this@NocFragment,id)
        binding.customerList.adapter = nocDataAdapter
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")

        }


        if (viewmodel.NocAndCompModelResponse?.hasActiveObservers() == true){
            viewmodel.NocAndCompModelResponse?.removeObservers(viewLifecycleOwner)
        }
        viewmodel.NocAndCompModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                binding.swipeLayout.isRefreshing=false
                hideLoader()
                AppLogger.log("NocAndComp Fragment card Data fetched successfully")
                try {
                    nocDataAdapter.setData(it.data.NOCCompliance!!)
                }catch (e:java.lang.Exception){
                    AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
                }
                AppLogger.log("size :${it.data.NOCCompliance?.size}")
                isDataLoaded = true
            }
            else if (it!=null) {
                AppLogger.log("NocAndComp Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("NocAndComp Fragment Something went wrong")
            }
        }
        binding.swipeLayout.setOnRefreshListener {
            nocDataAdapter.addLoading()
            viewmodel.NocAndCompRequestAll(id)
        }
        binding.addNew.setOnClickListener {
            val bm = AddNewNocCmpDialouge(
                object : AddNewNocCmpDialouge.AddNocCompDataListener {
                    override fun addNewData(){
                        showLoader()
                        viewmodel.NocAndCompRequestAll(AppController.getInstance().siteid)
                    }
                })
            bm.show(childFragmentManager,"sdg")
        }
    }

    override fun onViewPageSelected() {
        super.onViewPageSelected()
        if (!isDataLoaded){
            nocDataAdapter.addLoading()
            viewmodel.NocAndCompRequestAll(id)
        }
        AppLogger.log("onViewPageSelected NocAndComp")
    }

    override fun onDestroy() {
        if (viewmodel.NocAndCompModelResponse?.hasActiveObservers() == true)
            viewmodel.NocAndCompModelResponse?.removeObservers(viewLifecycleOwner)

        super.onDestroy()
    }

    override fun clickedItem(data:NocCompAllData,id:String,parentIndex:Int) {
        NocDetailsActivity.NocAndCompAlldata=data
        NocDetailsActivity.childIndex=parentIndex
        requireActivity().startActivity(Intent(requireContext(), NocDetailsActivity::class.java))

    }
}