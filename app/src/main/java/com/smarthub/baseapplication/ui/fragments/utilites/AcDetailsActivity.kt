package com.smarthub.baseapplication.ui.utilites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.ActivityAcDetailsBinding
import com.smarthub.baseapplication.model.siteInfo.utilitiesEquip.BatteryBank
import com.smarthub.baseapplication.ui.utilites.fragment.AcFragment

class AcDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityAcDetailsBinding
    companion object{
        var utilityAcData:ArrayList<BatteryBank>?=null
        var id:String?="448"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    fun initview(){
        val fragmentlist = ArrayList<Fragment>()
        fragmentlist.add(AcFragment())
        fragmentlist.add(AcFragment())
        val titels = ArrayList<String>()
        titels.add("AC #1")
        titels.add("AC #1")
        if (utilityAcData!=null){
//        binding.viewpager.adapter = BatteryViewpagerAdapter(supportFragmentManager, utilityAcData!!,id!!)
        binding.tabs.setupWithViewPager(binding.viewpager)}
        else Toast.makeText(binding.tabs.context,"Something Went Wrong", Toast.LENGTH_SHORT).show()

    }


}