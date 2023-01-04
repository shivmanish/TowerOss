package com.smarthub.baseapplication.ui.fragments.noc

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.NocNewActivityDetailsBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.siteInfo.nocAndCompModel.NocAndCompAllDataItem
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.fragments.noc.bottomSheetAdapters.*
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.bottomSheet.EarthingPoTableViewDialougeAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class NocDetailsActivity : BaseActivity(), NocListAdapter.NOCListListener {
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : NocNewActivityDetailsBinding
    lateinit var NocFragDataadapter:NocListAdapter
    lateinit var viewmodel: HomeViewModel
    companion object{
        var NocAndCompAlldata : NocAndCompAllDataItem?=null
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
        try {
            binding.IssueDate.text= NocAndCompAlldata?.ApplicationInitial?.get(0)?.IssueDate
        }catch (e:java.lang.Exception){
            AppLogger.log("Noc Fragment error : ${e.localizedMessage}")
            Toast.makeText(this,"Noc Fragment error :${e.localizedMessage}", Toast.LENGTH_LONG).show()
        }
        if (viewmodel?.NocAndCompModelResponse?.hasActiveObservers() == true){
            viewmodel?.NocAndCompModelResponse?.removeObservers(this)
        }
        viewmodel?.NocAndCompModelResponse?.observe(this) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                binding.swipeLayout.isRefreshing=false
                AppLogger.log("NocAndComp Fragment card Data fetched successfully")
                AppLogger.log("size :${it.data.item?.size}")

            }else if (it!=null) {
                Toast.makeText(this,"NocAndComp Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("NocAndComp Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("NocAndComp Fragment Something went wrong")
                Toast.makeText(this,"NocAndComp Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            viewmodel?.NocAndCompRequestAll(Id!!)
        }
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        NocFragDataadapter=NocListAdapter(this,this@NocDetailsActivity,NocAndCompAlldata!!)
        binding.list.adapter = NocFragDataadapter
    }

    override fun attachmentItemClicked() {
        Toast.makeText(this,"Item Clicked", Toast.LENGTH_SHORT).show()
    }

    override fun EditAuthorityDetails() {
        val bm = AuthorityDetailsDialougeAdapter(R.layout.noc_authority_details_dialouge_layout)
        bm.show(supportFragmentManager, "category")
        Toast.makeText(this , "Item 2 clicked" , Toast.LENGTH_SHORT).show()
    }

    override fun EditAppDetailsItem() {
        val bm = AppDetailsDialougeAdapter(R.layout.noc_application_details_dialouge_layout)
        bm.show(supportFragmentManager,"categoery")
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