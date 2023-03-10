package com.smarthub.baseapplication.ui.fragments.opcoTenancy.backhaulLink

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.databinding.BackhaulLinkFragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.BackhaulLinkData
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqInsidePremise
import com.smarthub.baseapplication.model.siteIBoard.newSiteAcquisition.SAcqOutsidePremise
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.opcoTenancy.OpcoTenancyActivity
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class BackhaulLinkFragment(var opcoData:OpcoTenencyAllData?,var parentIndex:Int):BaseFragment(), BAckhaulLinkFragAdapter.BackhaulItemListListener{
    lateinit var binding:BackhaulLinkFragmentBinding
    lateinit var adapter: BAckhaulLinkFragAdapter
    var viewmodel: HomeViewModel?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding=BackhaulLinkFragmentBinding.inflate(inflater)
        viewmodel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= BAckhaulLinkFragAdapter(requireContext(),this,opcoData?.BackhaulLink)
        binding.listItem.adapter=adapter
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
                    adapter.setData(it.data?.Operator?.get(parentIndex)?.BackhaulLink?.get(0))
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

        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
            adapter.addLoading()
            viewmodel?.opcoTenancyRequestAll(AppController.getInstance().siteid)
        }

    }

    override fun attachmentItemClicked() {
    }

    override fun MwUbrCLicked(data: BackhaulLinkData?) {
        MwUbrActivity.Backhauldata=data
        MwUbrActivity.parentIndex=parentIndex
        requireActivity().startActivity(Intent(requireContext(), MwUbrActivity::class.java))
    }

    override fun FiberOpticsClicked(data: BackhaulLinkData?) {
        BackhaulFibeOpticsrActivity.Backhauldata=data
        BackhaulFibeOpticsrActivity.parentIndex=parentIndex
        requireActivity().startActivity(Intent(requireContext(), BackhaulFibeOpticsrActivity::class.java))
    }


}