package com.smarthub.baseapplication.ui.site_lease_acquisition

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.databinding.FragmentSiteLeaseBinding

import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.SiteLeaseDataAdapter
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.SiteLeaseDataAdapterListener


class SiteLeaseAcqusitionFragment : Fragment(), SiteLeaseDataAdapterListener {

    private val ARG_PARAM1 = "param1"
    private val ARG_PARAM2 = "param2"
    lateinit var fragmentSiteLeaseBinding: FragmentSiteLeaseBinding
    lateinit var viewmodel: SiteLeaseAcqusitionViewModel
    lateinit var siteLeaseDataAdapter: SiteLeaseDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSiteLeaseBinding = FragmentSiteLeaseBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[SiteLeaseAcqusitionViewModel::class.java]
        initializeFragment()
        return fragmentSiteLeaseBinding.root
    }

    private fun initializeFragment() {
        fragmentSiteLeaseBinding.siteLeaseListItem.layoutManager = LinearLayoutManager(requireContext())
        siteLeaseDataAdapter = SiteLeaseDataAdapter(this@SiteLeaseAcqusitionFragment, ArrayList())
        fragmentSiteLeaseBinding.siteLeaseListItem.adapter = siteLeaseDataAdapter
        fragmentSiteLeaseBinding.addmore.setOnClickListener{
            var arraydata = ArrayList<String>()
            arraydata.add("anything")
          //  SiteLeaseDataAdapter.setData(arraydata)
        }
        viewmodel.fetchData()
        viewmodel.site_lease_data.observe(requireActivity(), Observer {
            // Data is get from server and ui work will be start from here
            println("this is called data is $it")
            var arraydata = ArrayList<String>()
            arraydata.add(it)
         //   SiteLeaseDataAdapter.setData(arraydata)
        })
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            SiteLeaseAcqusitionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun clickedItem() {
        requireActivity().startActivity(Intent(requireContext(), NewSiteAcquisitionActivity::class.java))

    }
}