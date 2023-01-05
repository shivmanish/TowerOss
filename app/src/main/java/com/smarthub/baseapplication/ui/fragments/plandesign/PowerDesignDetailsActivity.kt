package com.smarthub.baseapplication.ui.fragments.plandesign

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityPlanDesignDetailsBinding
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.serviceRequest.ServiceRequestAllDataItem
import com.smarthub.baseapplication.model.siteInfo.planAndDesign.PlanAndDesignDataItem
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.fragments.plandesign.fragment.*
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.HomeViewModel

class PowerDesignDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityPlanDesignDetailsBinding
    lateinit var viewmodel: HomeViewModel
    lateinit var adapter: PowerDesignDetailPageAdapter

    companion object{
        var planDesigndata : PlanAndDesignDataItem?=null
        var Id : String ="448"
        var index:Int?=0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlanDesignDetailsBinding.inflate(layoutInflater)
        viewmodel = ViewModelProvider(this)[HomeViewModel::class.java]
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        initview()
        binding.back.setOnClickListener {
            onBackPressed()
        }
        binding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }

        if (viewmodel?.PlanDesignModelResponse?.hasActiveObservers() == true){
            viewmodel?.PlanDesignModelResponse?.removeObservers(this)
        }
        viewmodel?.PlanDesignModelResponse?.observe(this) {
            if (it!=null && it.status == Resource.Status.LOADING){
                return@observe
            }
            if (it?.data != null && it.status == Resource.Status.SUCCESS){
                AppLogger.log("planDesign Fragment card Data fetched successfully")
                adapter.updateData(it.data.item!![0].PlanningAndDesign.get(index!!))
                AppLogger.log("size :${it.data.item?.size}")
            }else if (it!=null) {
                Toast.makeText(this,"planDesign Fragment error :${it.message}, data : ${it.data}", Toast.LENGTH_SHORT).show()
                AppLogger.log("planDesign Fragment error :${it.message}, data : ${it.data}")
            }
            else {
                AppLogger.log("planDesign Fragment Something went wrong")
                Toast.makeText(this,"planDesign Fragment Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }

        binding.swipeLayout.setOnRefreshListener {
            binding.swipeLayout.isRefreshing=false
            viewmodel?.planAndDesignRequestAll(Id)
        }

    }

fun initview(){
    adapter=PowerDesignDetailPageAdapter(supportFragmentManager, planDesigndata, Id)
    binding.viewpager.adapter = adapter
    binding.tabs.setupWithViewPager(binding.viewpager)

}
    override fun onDestroy() {
        if (viewmodel?.PlanDesignModelResponse?.hasActiveObservers() == true){
            viewmodel?.PlanDesignModelResponse?.removeObservers(this)
        }
        super.onDestroy()
    }

}