package com.smarthub.baseapplication.ui.fragments.qat

import android.os.Bundle
import android.widget.TextView
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityQatBinding
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Category
import com.smarthub.baseapplication.ui.fragments.qat.adapter.PageAdapterQat

class QatActivity : BaseActivity() {
    companion object{
        var data : List<Category>?=null
        var mainindex:Int?=-1
    }
    lateinit var binding: ActivityQatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQatBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        init()
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun init() {
        if (data!=null) {
            binding.viewpager.adapter = PageAdapterQat(supportFragmentManager, data!!, mainindex!!)
            binding.tabs.setupWithViewPager(binding.viewpager)
            val tabsCount = binding.tabs.tabCount
            for (j in 0 until tabsCount) {
                val view = layoutInflater.inflate(R.layout.tabbutton_faq, null)
                val textview = view.findViewById<TextView>(R.id.tabtext)
                val text = data!![j].QATCategory
                textview.text = text
                binding.tabs.getTabAt(j)?.customView = view
            }
        }

    }

}