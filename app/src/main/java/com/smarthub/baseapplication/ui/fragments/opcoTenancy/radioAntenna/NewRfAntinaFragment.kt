package com.smarthub.baseapplication.ui.fragments.opcoTenancy.radioAntenna

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.RfAntinaFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.RadioAnteenaAndRRUData
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.bottomDialouge.AddNewRfAntennaAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class NewRfAntinaFragment(var opcodata: OpcoTenencyAllData?,var parentIndex:Int) :Fragment(), RfAntennaItemListener {

    var binding : RfAntinaFragmentBinding?=null
    lateinit var adapter: RfAnteenaFragAdapter
    var viewmodel: HomeViewModel?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = RfAntinaFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = RfAnteenaFragAdapter(requireContext(),this@NewRfAntinaFragment,opcodata?.RadioAntennaAndRRU!!)
        binding?.listItem?.adapter=adapter

        binding?.addItemsLayout?.setOnClickListener {
            val dalouge = AddNewRfAntennaAdapter(R.layout.rf_anteena_list_item_dialouge)
            dalouge.show(childFragmentManager,"")
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
                    adapter.setOpData(it.data?.Operator?.get(parentIndex)?.RadioAntennaAndRRU)
                }catch (e:Exception){
                    AppLogger.log("error in RadinAntenna RRU Frag during set data ")
                }
            }else if (it!=null) {
                Toast.makeText(requireContext(),"OpcoSiteInfo Frag error :${it.message}", Toast.LENGTH_SHORT).show()
            }else{
                AppLogger.log("OpcoSiteInfo Frag Something went wrong")
                Toast.makeText(requireContext(),"OpcoSiteInfo Frag Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding?.swipeLayout?.setOnRefreshListener {
            binding?.swipeLayout?.isRefreshing=false
            adapter.addLoading()
            viewmodel?.opcoTenancyRequestAll(AppController.getInstance().siteid)

        }
    }



    override fun clickedItem(data: RadioAnteenaAndRRUData) {
        RadioAnteenaActivity.AntennaData =data
        requireActivity().startActivity(Intent(requireContext(), RadioAnteenaActivity::class.java))

    }
}