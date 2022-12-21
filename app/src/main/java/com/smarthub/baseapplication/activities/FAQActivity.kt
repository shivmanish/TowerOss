package com.smarthub.baseapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.ui.adapter.PageAdapter_faq

class FAQActivity : BaseActivity() {
    private val pageTitles = arrayOf("Ventos","Catering","Cancellations","Reservations","ABC","XYZ")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        val viewPager=findViewById<ViewPager>(R.id.viewpager)
        viewPager.adapter= PageAdapter_faq(supportFragmentManager)
        val tabLayout=findViewById<TabLayout>(R.id.tabLayout)
        tabLayout.setupWithViewPager(viewPager)
        val tabsCount = tabLayout.tabCount
        for(j in 0 until tabsCount)
        {
            val view = layoutInflater.inflate(R.layout.tabbutton_faq,null)
            val textview = view.findViewById<TextView>(R.id.tabtext)
            val text = tabLayout.getTabAt(j)?.text
            textview.setText(text)
            tabLayout.getTabAt(j)?.setCustomView(view)
        }







    }
}