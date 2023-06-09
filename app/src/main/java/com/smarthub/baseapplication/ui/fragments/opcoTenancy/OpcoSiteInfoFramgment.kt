package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OpcoInfoFregmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.NewOpcoInfoData
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.Opcoinfo
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo.OpcoSiteInfoEditDialouge
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.opcoInfo.OperationsItemsEditDialouge
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class OpcoSiteInfoFramgment(var opcodata: ArrayList<NewOpcoInfoData>?,var parentIndex:Int) :BaseFragment(), OpcoSiteInfoFragAdapter.OpcoInfoLisListener {
    var binding : OpcoInfoFregmentBinding?=null
    var viewmodel: HomeViewModel?=null
    lateinit var adapter: OpcoSiteInfoFragAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = OpcoInfoFregmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OpcoSiteInfoFragAdapter(this@OpcoSiteInfoFramgment,opcodata!!)
        binding?.listItem?.adapter=adapter
        binding?.swipeLayout?.setOnRefreshListener {
            viewmodel?.opcoTenancyRequestAll(AppController.getInstance().siteid)
        }
        if (viewmodel?.opcoTenencyModelResponse?.hasActiveObservers() == true)
            viewmodel?.opcoTenencyModelResponse?.removeObservers(viewLifecycleOwner)
        viewmodel?.opcoTenencyModelResponse?.observe(viewLifecycleOwner) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it!=null && it.status == Resource.Status.SUCCESS){
                binding?.swipeLayout?.isRefreshing = false
                AppLogger.log("OpcoSiteInfo Frag Data fetched successfully")
                try {
                    adapter.setData(it.data?.Operator?.get(parentIndex)?.Opcoinfo?.get(0))
                }catch (e:Exception){
                    AppLogger.log("error in opcoInfo Frag during set data ")
                }
            }else if (it!=null) {
                Toast.makeText(requireContext(),"OpcoSiteInfo Frag error :${it.message}", Toast.LENGTH_SHORT).show()
            }else{
                AppLogger.log("OpcoSiteInfo Frag Something went wrong")
                Toast.makeText(requireContext(),"OpcoSiteInfo Frag Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun attachmentItemClicked() {

    }

    override fun operationsItemClicked(data: Opcoinfo) {
        val bottomSheetDialogFragment = OperationsItemsEditDialouge(R.layout.opco_operations_team_dialouge,data,opcodata?.get(0)?.id.toString())
        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }

    override fun opcoSiteInfoItemClicked(data: Opcoinfo) {
        val bottomSheetDialogFragment = OpcoSiteInfoEditDialouge(R.layout.opco_info_site_dialouge_layout,data,opcodata?.get(0)?.id.toString(),
        object : OpcoSiteInfoEditDialouge.OpcoInfoListener{
            override fun updatedData(data: Opcoinfo) {
                adapter.updateOpcoInfoData(data)
            }

        })

        bottomSheetDialogFragment.show(childFragmentManager,"category")

    }


}