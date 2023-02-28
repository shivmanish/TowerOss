package com.smarthub.baseapplication.ui.fragments.noc

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.NocNewActivityDetailsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteIBoard.newNocAndComp.NocCompAllData
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.ApplicationInitial
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.AuthorityDetails
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.NocAndCompAllDataItem
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters.*
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.EarthingPoTableViewDialougeAdapter
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class NocDetailsActivity : BaseActivity() {
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : NocNewActivityDetailsBinding
//    lateinit var NocFragDataadapter:NocListAdapter
    lateinit var adapter:NocCompPageAdapter
    lateinit var viewmodel: HomeViewModel
    companion object{
        var NocAndCompAlldata : NocCompAllData?=null
        var Id : String?="448"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NocNewActivityDetailsBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initViews()
        adapter=NocCompPageAdapter(supportFragmentManager,NocAndCompAlldata)
        binding.subTitle.text=AppController.getInstance().siteName
        binding.viewpager.adapter=adapter
        binding.tabs.setupWithViewPager(binding.viewpager)

    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

//    override fun attachmentItemClicked() {
//        Toast.makeText(this,"Item Clicked", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun EditAuthorityDetails(authorityDetails: AuthorityDetails?) {
//        val bm = AuthorityDetailsDialougeAdapter(R.layout.noc_authority_details_dialouge_layout,authorityDetails)
//        bm.show(supportFragmentManager, "category")
//        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
//    }
//
//    override fun EditAppDetailsItem(applicationDetailsData: ApplicationInitial?) {
//        val bm = AppDetailsDialougeAdapter(R.layout.noc_application_details_dialouge_layout,applicationDetailsData)
//        bm.show(supportFragmentManager,"categoery")
//        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
//    }
//
//    override fun editPoClicked(position: Int) {
//        var bm = PoEditDialougeDapter(R.layout.noc_po_edit_dialouge)
//        bm.show(supportFragmentManager,"categoery")
//        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
//    }
//
//    override fun viewPoClicked(position: Int) {
//        var bm = EarthingPoTableViewDialougeAdapter(R.layout.earthing_po_item_dialouge)
//        bm.show(supportFragmentManager, "category")
//    }
//
//    override fun editfeePaymentClicked(position: Int) {
//        var bm = FeePaymentEditDialougeAdapter(R.layout.noc_fee_pay_edit_dialouge)
//        bm.show(supportFragmentManager,"categoery")
//        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
//    }
//
//    override fun viewfeePaymentClicked(position: Int) {
//        var bm = FeePaymentViewDialougeAdapter(R.layout.noc_fee_payment_view_dialouge)
//        bm.show(supportFragmentManager,"categoery")
//        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
//    }


}