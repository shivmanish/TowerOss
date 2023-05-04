package com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing
import android.graphics.Color
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityTowerEarthingFragmentBinding
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.FilterdTwrData
import com.smarthub.baseapplication.model.siteIBoard.newTowerCivilInfra.NewTowerCivilAllData
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.towerCivilInfra.earthing.adapters.TowerEarthingAdapter
import com.smarthub.baseapplication.utils.AppController

class TowerEarthingFragment : BaseActivity() {
    lateinit var binding : ActivityTowerEarthingFragmentBinding
    companion object{
        var EarthingModelData : NewTowerCivilAllData?=null
        var Id : String?="448"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTowerEarthingFragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        binding.titel.text="Earthing"
        binding.subTitle.text= AppController.getInstance().siteName
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.addMore.setOnClickListener(){
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }
        if (EarthingModelData !=null)
            binding.viewpager.adapter = TowerEarthingAdapter(supportFragmentManager, EarthingModelData?.TowerAndCivilInfraEarthing,
                EarthingModelData)
        binding.tabs.setupWithViewPager(binding.viewpager)
        if(binding.tabs.tabCount==1) {
            binding.tabs.setBackgroundColor(Color.parseColor("#ffffff"))
            binding.tabs.setSelectedTabIndicatorColor(Color.parseColor("#ffffff"))
        }
        if(binding.tabs.tabCount<=3) {
            binding.tabs.tabMode = TabLayout.MODE_FIXED

        }else {
            binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE
        }

    }
    fun filterTowerList(data:ArrayList<NewTowerCivilAllData>):ArrayList<FilterdTwrData>{
        val filteredData:ArrayList<FilterdTwrData> = ArrayList()
        filteredData.clear()
        for(i in 0..data.size.minus(1)){
            val tempdData = FilterdTwrData()
            if (data.get(i).TowerAndCivilInfraEarthing?.isNotEmpty()==true){
                tempdData.TowerDetails=data.get(i)
                tempdData.index=i
                filteredData.add(tempdData)
            }
        }
        return filteredData
    }


}