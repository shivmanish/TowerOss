package com.smarthub.baseapplication.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchActivityBinding
import com.smarthub.baseapplication.ui.fragments.site_detail.SiteDetailFragment
import com.smarthub.baseapplication.viewmodels.SearchActivityViewModel

class SearchActivity : BaseActivity() {
    private var dataBinding: SearchActivityBinding? = null
    private lateinit var mViewModel: SearchActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = this.supportActionBar
        actionBar?.hide()
        dataBinding = SearchActivityBinding.inflate(layoutInflater)
        mViewModel = ViewModelProvider(this)[SearchActivityViewModel::class.java]
        setContentView(dataBinding?.root)
        dataBinding?.clearSearchQuery?.setOnClickListener {
            val siteFragment = SiteDetailFragment()
            addFragment(siteFragment)
        }
        setSearchFilter()
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
            transaction.add(R.id.nav_main, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }

    private fun setSearchFilter() {
        val data: List<String> = mViewModel.getFlowData()
//        dataBinding!!.flowlayout.removeAllViews()
//        for (i in 0 until data.size) {
//            val child: View =
//                layoutInflater.inflate(R.layout.flowcontainer, dataBinding!!.flowlayout, false)
//            var titeltext: TextView = child.findViewById(R.id.titel)
//            titeltext.text = data[i]
//            dataBinding!!.flowlayout.addView(child)
//        }
    }
}