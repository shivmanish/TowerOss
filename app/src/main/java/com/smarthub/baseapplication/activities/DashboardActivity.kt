package com.smarthub.baseapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityMainBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.ui.fragments.site_detail.SiteDetailViewModel
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.viewmodels.MainViewModel

class  DashboardActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SiteDetailViewModel
    private lateinit var mainViewModel: MainViewModel
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel = ViewModelProvider(this)[SiteDetailViewModel::class.java]
        initializeCustomActionBar()
        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null
//        mainViewModel.isActionbarHide.observe(this, Observer {
//            if (it)
//            binding.searchBoxContainer.mainActionBar.visibility = View.GONE
//            else
                binding.searchBoxContainer.mainActionBar.visibility = View.VISIBLE
//        })

        findViewById<View>(R.id.search).setOnClickListener {
            var intent = Intent(this,SearchActivity::class.java)
            startActivity(intent)
        }

//        navController.navigate(R.id.navigation_menu)
        AppLogger.log("access token :" + AppPreferences.getInstance().token)
        AppLogger.log("refresh token :" + AppPreferences.getInstance().refresh)
    }
    private fun initializeCustomActionBar() {
        val actionBar: ActionBar? = this.supportActionBar
        actionBar?.hide()
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
            transaction.replace(R.id.container, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }
}