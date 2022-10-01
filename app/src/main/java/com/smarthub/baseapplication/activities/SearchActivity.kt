package com.smarthub.baseapplication.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchActivityBinding
import com.smarthub.baseapplication.ui.site_detail.SiteDetailFragment
import com.smarthub.baseapplication.utils.Utility

class SearchActivity : AppCompatActivity() {
    private var dataBinding : SearchActivityBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = this.supportActionBar
        actionBar?.hide()
        dataBinding = SearchActivityBinding.inflate(layoutInflater)
        setContentView(dataBinding?.root)
        dataBinding?.clearSearchQuery?.setOnClickListener {
            val siteFragment= SiteDetailFragment()
            addFragment(siteFragment)
//            var intent = Intent(this, SiteDetailActivity::class.java)
//            startActivity(intent)
//            finish()
        }

    }
    fun addFragment(fragment: Fragment?) {
        val backStateName: String = supportFragmentManager.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.replace(R.id.nav_main, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }
}