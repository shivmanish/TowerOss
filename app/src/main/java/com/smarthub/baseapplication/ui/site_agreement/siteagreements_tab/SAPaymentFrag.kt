package com.smarthub.baseapplication.ui.site_agreement.siteagreements_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PaymentFragmentBinding
import com.smarthub.baseapplication.ui.adapter.common.ImageAttachmentAdapter
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.site_agreement.tableadapter.PaymentTableAdapter
import com.smarthub.baseapplication.ui.site_agreement.tableadapter.SANominalsFragmentAdapter
import com.smarthub.baseapplication.ui.site_agreement.tableadapter.SAPaymentAdapter

class SAPaymentFrag :BaseFragment(), PaymentTableAdapter.PaymentInfoListListener{
    lateinit var adapter : SAPaymentAdapter

    var binding : PaymentFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PaymentFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= SAPaymentAdapter(requireContext(),this@SAPaymentFrag)
        binding?.listItem?.adapter = adapter
        initViews(view)
    }

    fun initViews(view: View){

    }

    fun itemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editClicked(position: Int) {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()

    }

    override fun viewClicked(position: Int) {
        TODO("Not yet implemented")
    }
}