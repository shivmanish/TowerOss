package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.smarthub.baseapplication.R
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.databinding.NewCustomerDetailFragmentBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.model.siteIBoard.newOpcoTenency.OpcoTenencyAllData
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel


class OpcoTenancyActivity : BaseActivity() {
    private var profileViewModel : SiteInfoViewModel?=null
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : NewCustomerDetailFragmentBinding

    companion object{
        var Opcodata : OpcoTenencyAllData?=null
        var parentIndex:Int?=0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NewCustomerDetailFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun mapUiData(siteInfoDropDownData: SiteInfoDropDownData){
        this.siteInfoDropDownData = siteInfoDropDownData
    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.siteId.text=AppController.getInstance().siteName
        binding.subTitle.text=Opcodata?.Opcoinfo?.get(0)?.SRInfo01?.get(0)?.OpcoSiteId
        try {
            binding.rfiDate.text =
                Utils.getFormatedDate(Opcodata?.Opcoinfo?.get(0)?.SRInfo01?.get(0)?.RFIAcceptanceDate?.substring(
                    0,
                    10)!!, "dd-MMM-yyyy")

        }catch (e:Exception) {
            e.printStackTrace()
        }
        binding.titel.text= Opcodata?.Opcoinfo?.get(0)?.SRInfo01?.get(0)?.OpcoName
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }

        AppLogger.log("status Opcodata","Opcodata size : "+Opcodata)
        binding.viewpager.adapter = OpcoTenancyPageAdapter(supportFragmentManager, Opcodata!!, parentIndex!!)
        binding.tabs.setupWithViewPager(binding.viewpager)
        binding.tabs.setOnTabSelectedListener(onTabSelectedListener(binding.viewpager))
        binding.viewpager.beginFakeDrag()
        for (i in 0..binding.tabs.tabCount.minus(1)){
            if (i==0)
                binding.tabs.getTabAt(i)?.view?.setBackgroundResource(R.color.white)
            val itemBinding = TabNameItemBinding.inflate(layoutInflater)
            itemBinding.tabName.text = binding.viewpager.adapter?.getPageTitle(i)
            itemBinding.tabName.textSize = 12f
            binding.tabs.getTabAt(i)?.customView = itemBinding.root
        }

        profileViewModel= ViewModelProvider(this)[SiteInfoViewModel::class.java]

    }

    private fun onTabSelectedListener(pager: ViewPager): OnTabSelectedListener {
        return object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.color.white)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.setBackgroundResource(R.color.tab_deselected)
            }
            override fun onTabReselected(tab: TabLayout.Tab) {}
        }
    }

}