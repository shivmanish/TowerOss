package com.smarthub.baseapplication.ui.fragments.qat

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityQatBinding
import com.smarthub.baseapplication.ui.fragments.qat.adapter.PageAdapterQat

class QatActivity : AppCompatActivity() {
    lateinit var binding: ActivityQatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    fun init() {
        binding.viewpager.adapter = PageAdapterQat(supportFragmentManager)
        binding.tabs.setupWithViewPager(binding.viewpager)
        val tabsCount = binding.tabs.tabCount
        for (j in 0 until tabsCount) {
            val view = layoutInflater.inflate(R.layout.tabbutton_faq, null)
            val textview = view.findViewById<TextView>(R.id.tabtext)
            val text = binding.tabs.getTabAt(j)?.text
            textview.setText(text)
            binding.tabs.getTabAt(j)?.setCustomView(view)
        }

    }

}