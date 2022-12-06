package com.smarthub.baseapplication.ui.utilites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.databinding.ActivityDgDetailsBinding
import com.smarthub.baseapplication.ui.utilites.adapter.DGViewpagerAdapter
import com.smarthub.baseapplication.ui.utilites.fragment.DGFragment

class DGDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityDgDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDgDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }

    fun initview(){
        val fragmentlist = ArrayList<Fragment>()
        fragmentlist.add(DGFragment())
        fragmentlist.add(DGFragment())
        val titels = ArrayList<String>()
        titels.add("DG#1")
        titels.add("DG#2")
        binding.viewpager.adapter = DGViewpagerAdapter(supportFragmentManager,fragmentlist,titels)
        binding.tabs.setupWithViewPager(binding.viewpager)
    }


}