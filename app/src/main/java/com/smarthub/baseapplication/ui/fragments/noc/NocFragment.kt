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
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.NocAndCompAllDataItem
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.NocDataAdapter
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.NocDataAdapterListener
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters.CreateNocBottomSheet
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class NocFragment(var id : String): BaseFragment(), NocDataAdapterListener {
    lateinit var NocCompBinding: NocAndCompFragmentBinding
    lateinit var viewmodel: HomeViewModel
    var isDataLoaded = false
    lateinit var nocDataAdapter: NocDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        NocCompBinding = NocAndCompFragmentBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return NocCompBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NocCompBinding.customerList.layoutManager = LinearLayoutManager(requireContext())
        nocDataAdapter = NocDataAdapter(requireContext(),this@NocFragment,id)
        NocCompBinding.customerList.adapter = nocDataAdapter
        NocCompBinding.addMore.setOnClickListener(){
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
                NocCompBinding.swipeLayout.isRefreshing=false
                AppLogger.log("NocAndComp Fragment card Data fetched successfully")
                try {
                    nocDataAdapter.setData(it.data.item!![0].NOCCompliance)
                }catch (e:java.lang.Exception){
                    AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
                    Toast.makeText(context,"Noc Fragment error :${e.localizedMessage}",Toast.LENGTH_LONG).show()
                }
                AppLogger.log("size :${it.data.item?.size}")
                isDataLoaded = true
            }else if (it!=null) {
                Toast.makeText(requireContext(),"NocAndComp Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("NocAndComp Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("NocAndComp Fragment Something went wrong")
                Toast.makeText(requireContext(),"NocAndComp Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
        NocCompBinding.swipeLayout.setOnRefreshListener {
            viewmodel.NocAndCompRequestAll(id)
        }
        NocCompBinding.addNew.setOnClickListener(){
            val dalouge = CreateNocBottomSheet(R.layout.create_noc_site_info_dialoge)
            dalouge.show(childFragmentManager,"")

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

    override fun clickedItem(data:NocAndCompAllDataItem,id:String) {
        NocDetailsActivity.NocAndCompAlldata=data
        NocDetailsActivity.Id=id
        requireActivity().startActivity(Intent(requireContext(), NocDetailsActivity::class.java))

    }
}