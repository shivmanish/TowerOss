package com.smarthub.baseapplication.ui.fragments.noc

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.NocNewActivityDetailsBinding
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters.FeePaymentEditDialougeAdapter
import com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters.FeePaymentViewDialougeAdapter
import com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters.PoEditDialougeDapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.EarthingInfoFragmentAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.EarthingPoTableViewDialougeAdapter
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.EditEarthingPOTableBottomSheet

class NocDetailsActivity : BaseActivity(), NocListAdapter.NOCListListener {
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : NocNewActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NocNewActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews(){
        binding.list.adapter = NocListAdapter(this,this@NocDetailsActivity)
    }

    override fun attachmentItemClicked() {
        Toast.makeText(this,"Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun EditInstallationAcceptence() {
        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun EditEarthingItem() {
        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun editPoClicked(position: Int) {
        var bm = PoEditDialougeDapter(R.layout.noc_po_edit_dialouge)
        bm.show(supportFragmentManager,"categoery")
        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewPoClicked(position: Int) {
        var bm = EarthingPoTableViewDialougeAdapter(R.layout.earthing_po_item_dialouge)
        bm.show(supportFragmentManager, "category")
    }

    override fun editfeePaymentClicked(position: Int) {
        var bm = FeePaymentEditDialougeAdapter(R.layout.noc_fee_pay_edit_dialouge)
        bm.show(supportFragmentManager,"categoery")
        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun viewfeePaymentClicked(position: Int) {
        var bm = FeePaymentViewDialougeAdapter(R.layout.noc_fee_payment_view_dialouge)
        bm.show(supportFragmentManager,"categoery")
        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }


}