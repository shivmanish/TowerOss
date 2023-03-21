package com.smarthub.baseapplication.ui.utilites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityFireExtinguisherDetailsBinding
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.BatteryBank
import com.smarthub.baseapplication.ui.dialog.utils.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.utilites.fragment.FireExtinguisherFragment

class FireExtinguisherDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityFireExtinguisherDetailsBinding
    companion object{
        var utilityFireExtData:ArrayList<BatteryBank>?=null
        var id:String?="448"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireExtinguisherDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    fun initview(){
        binding.addMore.setOnClickListener{
            val dalouge = CommonBottomSheetDialog(R.layout.add_more_botom_sheet_dailog)
            dalouge.show(supportFragmentManager,"")
        }
        val fragmentlist = ArrayList<Fragment>()
        fragmentlist.add(FireExtinguisherFragment())
        fragmentlist.add(FireExtinguisherFragment())
        val titels = ArrayList<String>()
        titels.add("Fire Extinguisher#1")
        titels.add("Fire Extinguisher#2")
        if(BatteryDetailsActivity.utilityBatteryBankData !=null) {
//        binding.viewpager.adapter = BatterryBankViewpagerAdapter(supportFragmentManager,utilityFireExtData!!,id!!)
        binding.tabs.setupWithViewPager(binding.viewpager)
        }
        else Toast.makeText(binding.tabs.context,"Something Went Wrong", Toast.LENGTH_SHORT).show()


    }


}