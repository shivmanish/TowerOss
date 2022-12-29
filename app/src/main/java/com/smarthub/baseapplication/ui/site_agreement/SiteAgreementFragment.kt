package com.smarthub.baseapplication.ui.site_agreement

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.FragmentSiteLeaseAcquitionBinding
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.SiteLeaseDataAdapter
import com.smarthub.baseapplication.ui.fragments.sitedetail.adapter.SiteLeaseDataAdapterListener


class SiteAgreementFragment : BaseFragment(), SiteLeaseDataAdapterListener {
    val ARG_PARAM1 = "param1"
    lateinit var fragmentSiteLeaseBinding: FragmentSiteLeaseAcquitionBinding
    lateinit var viewmodel: SiteAgreementViewModel
    lateinit var siteLeaseDataAdapter: SiteLeaseDataAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentSiteLeaseBinding = FragmentSiteLeaseAcquitionBinding.inflate(inflater, container, false)
        viewmodel = ViewModelProvider(requireActivity())[SiteAgreementViewModel::class.java]
        return fragmentSiteLeaseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSiteLeaseBinding.siteLeaseListItem.layoutManager = LinearLayoutManager(requireContext())
        siteLeaseDataAdapter = SiteLeaseDataAdapter(this@SiteAgreementFragment, ArrayList())
        fragmentSiteLeaseBinding.siteLeaseListItem.adapter = siteLeaseDataAdapter
        var arraydata = ArrayList<String>()
        arraydata.add("anything")
        arraydata.add("anything")
        siteLeaseDataAdapter.setData(arraydata)
        //   viewmodel.fetchData()

        viewmodel.fetchDropDown()
        viewmodel.site_lease_data.observe(requireActivity(), Observer {
            // Data is get from server and ui work will be start from here
            println("this is called data is $it")
            var arraydata = ArrayList<String>()
            arraydata.add(it)
            siteLeaseDataAdapter.setData(arraydata)
        })

        fragmentSiteLeaseBinding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(childFragmentManager,"")
        }
    }



    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            SiteAgreementFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun clickedItem() {
        requireActivity().startActivity(Intent(requireContext(), SiteAgreementCaredItemActivity::class.java))

    }
}