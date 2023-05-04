package com.smarthub.baseapplication.ui.fragments.opcoTenancy

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.databinding.OpcofragmnetnewBinding
import com.smarthub.baseapplication.model.siteInfo.opcoInfo.OpcoDataItem
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.dynamicui.DynamicTabFragment
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.SiteInfoViewModel


class OpcoTenancyActivityNew : BaseActivity() {
    private var profileViewModel : SiteInfoViewModel?=null
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : OpcofragmnetnewBinding

    companion object{
        var Opcodata : OpcoDataItem?=null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OpcofragmnetnewBinding.inflate(layoutInflater)
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
        binding.siteId.text=Opcodata?.Opcoinfo?.get(0)?.OpcoSiteID
        binding.subTitle.text=Opcodata?.Opcoinfo?.get(0)?.OpcoSiteID
        binding.rfiDate.text= Opcodata?.Opcoinfo?.get(0)?.rfiAcceptanceDate
        binding.titel.text= Opcodata?.Opcoinfo?.get(0)?.OpcoName
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }

        AppLogger.log("status Opcodata","Opcodata size : "+Opcodata)
        profileViewModel= ViewModelProvider(this)[SiteInfoViewModel::class.java]
        addFragment(R.id.frag,DynamicTabFragment("23"),"dsf")
    }

    protected fun addFragment(
        @IdRes containerViewId: Int,
        fragment: Fragment?,
        fragmentTag: String?
    ) {
        supportFragmentManager
            .beginTransaction()
            .add(containerViewId, fragment!!, fragmentTag)
            .disallowAddToBackStack()
            .commit()
    }


}