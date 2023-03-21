package com.smarthub.baseapplication.ui.utilites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.ActivitySpdDetailsBinding
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.BatteryBank
import com.smarthub.baseapplication.ui.utilites.fragment.FireExtinguisherFragment

class SurgeProtectionDeviceDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivitySpdDetailsBinding
    companion object{
        var utilitySPDData:ArrayList<BatteryBank>?=null
        var id:String?="448"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpdDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    fun initview(){
        val fragmentlist = ArrayList<Fragment>()
        fragmentlist.add(FireExtinguisherFragment())
        fragmentlist.add(FireExtinguisherFragment())
        val titels = ArrayList<String>()
        titels.add("SPD #1")
        titels.add("SPD #2")
        if(utilitySPDData!=null){
//        binding.viewpager.adapter = BatteryViewpagerAdapter(supportFragmentManager,utilitySPDData!!,id!!)
        binding.tabs.setupWithViewPager(binding.viewpager)}
        else Toast.makeText(binding.tabs.context,"Something Went Wrong", Toast.LENGTH_SHORT).show()

    }


}