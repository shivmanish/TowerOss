package com.smarthub.baseapplication.ui.site_agreement.siteagreements_tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.PaymentFragmentBinding
import com.smarthub.baseapplication.model.siteInfo.siteAgreements.SiteacquisitionPayment
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.ui.site_agreement.dialogs.SAPaymentEditBottomSheet
import com.smarthub.baseapplication.ui.site_agreement.dialogs.SAPaymentViewBottomSheet
import com.smarthub.baseapplication.ui.site_agreement.tableadapter.PaymentTableAdapter
import com.smarthub.baseapplication.ui.site_agreement.tableadapter.SAPaymentAdapter

class SAPaymentFrag(val payment: List<SiteacquisitionPayment>?) :BaseFragment(), PaymentTableAdapter.PaymentInfoListListener{
    lateinit var adapter : SAPaymentAdapter

    var binding : PaymentFragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = PaymentFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= SAPaymentAdapter(requireContext(),this@SAPaymentFrag,payment)
        binding?.listItem?.adapter = adapter
        initViews(view)
    }

    fun initViews(view: View){

    }

    fun itemClicked() {
        Toast.makeText(requireContext(),"Item Clicked",Toast.LENGTH_SHORT).show()
    }

    override fun editClicked(position: Int) {
        var payment = SAPaymentEditBottomSheet(R.layout.sa_payment_edit_dialog)
        payment?.show(childFragmentManager,"category")
    }

    override fun viewClicked(position: Int) {
        var viewPayment = SAPaymentViewBottomSheet(R.layout.sa_payment_view_dialog)
        viewPayment?.show(childFragmentManager,"category")
    }
}