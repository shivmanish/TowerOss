package com.smarthub.baseapplication.ui.fragments.towerCivilInfra
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityTwrInfraDetailsBinding
import com.smarthub.baseapplication.model.siteInfo.towerAndCivilInfra.TowerAndCivilInfraTowerModel
import com.smarthub.baseapplication.network.pojo.site_info.SiteInfoDropDownData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class TwrInfraDetails : BaseActivity() {
    var viewmodel: HomeViewModel?=null
    var siteInfoDropDownData: SiteInfoDropDownData?=null
    lateinit var binding : ActivityTwrInfraDetailsBinding
    companion object{
        var TowerModelData : ArrayList<TowerAndCivilInfraTowerModel>?=null
        var Id : String?="448"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTwrInfraDetailsBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        setContentView(binding?.root)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initViews()


    }

    private fun initViews(){
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }
        binding.viewpager.adapter = TowerPageAdapter(supportFragmentManager,TowerModelData,Id)
        binding.tabs.setupWithViewPager(binding.viewpager)

    }



}