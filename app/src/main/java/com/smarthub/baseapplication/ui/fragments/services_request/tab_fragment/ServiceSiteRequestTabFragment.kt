package com.smarthub.baseapplication.ui.fragments.services_request.tab_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoFregmentBinding
import com.smarthub.baseapplication.ui.dialog.services_request.*
import com.smarthub.baseapplication.ui.fragments.services_request.adapter.ServicesSiteRequestAdapter
import com.smarthub.baseapplication.viewmodels.HomeViewModel
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel




class ServiceSiteRequestTabFragment : Fragment(), ServicesSiteRequestAdapter.ServicesRequestLisListener {
    var siteViewModel : SiteInfoViewModel?=null

    var homeViewModel : HomeViewModel?=null
    var binding : OpcoInfoFregmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = OpcoInfoFregmentBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setList()
    }

    private fun setList(){
        val projectListAdapter = ServicesSiteRequestAdapter(this@ServiceSiteRequestTabFragment)
        binding!!.listItem.layoutManager = LinearLayoutManager(context)
        binding!!.listItem.adapter = projectListAdapter
       // projectListAdapter.addLoading("loading")
        if (homeViewModel?.getServiceRequest?.hasActiveObservers() == true)
            homeViewModel?.getServiceRequest?.removeObservers(viewLifecycleOwner)
           homeViewModel?.getServiceRequest?.observe(viewLifecycleOwner){

            if (it?.data != null){
                val list :ArrayList<Any> = ArrayList()
                list.addAll(it.data)
                binding?.listItem?.adapter = ServicesSiteRequestAdapter(this@ServiceSiteRequestTabFragment)
              //  projectListAdapter.updateList(list)
//                projectListAdapter.updateList(it.data!!)
            }else{
             //   projectListAdapter.addNoData("no_data")
            }
        }
    //    homeViewModel?.fetchServiceRequestData(templateName : String)

    }
    override fun attachmentItemClicked() {

    }
    override fun detailsItemClicked(template : String) {
        val bottomSheetDialogFragment = SRDetailsBottomSheet(R.layout.sr_details_bottom_sheet_dialog, homeViewModel!!,template)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun operationInfoDetailsItemClicked() {
        val bottomSheetDialogFragment = EquipmentDetailsBottomSheetDialog(R.layout.equipment_bottom_sheet)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun geoConditionsDetailsItemClicked() {
        val bottomSheetDialogFragment = RadioAntennasBottomSheetDialog(R.layout.radio_antina_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun siteAccessDetailsItemClicked() {
        val bottomSheetDialogFragment = BachhualLinkBottomSheet(R.layout.backhaul_link_list_item)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }
    override fun requestinfoClicked() {
        val bottomSheetDialogFragment = RequestInfoBottomSheet(R.layout.request_info_bottom_sheet_dialog)
        bottomSheetDialogFragment.show(childFragmentManager,"category")
    }


}