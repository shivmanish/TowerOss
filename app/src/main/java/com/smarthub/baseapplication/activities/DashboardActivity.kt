package com.smarthub.baseapplication.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityMainBinding
import com.smarthub.baseapplication.ui.site_detail.SiteDetailViewModel
import com.smarthub.baseapplication.viewmodels.MainViewModel

class DashboardActivity : BaseActivity() {

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
        mainViewModel.isActionbarHide.observe(this, Observer {
            if (it)
            binding.searchBoxContainer.mainActionBar.visibility = View.GONE
            else
                binding.searchBoxContainer.mainActionBar.visibility = View.VISIBLE
        })
    }

    private fun initializeCustomActionBar() {
        val actionBar: ActionBar? = this.supportActionBar
        actionBar?.hide()
    }
}