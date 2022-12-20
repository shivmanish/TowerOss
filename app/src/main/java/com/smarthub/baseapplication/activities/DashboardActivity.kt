package com.smarthub.baseapplication.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityMainBinding
import com.smarthub.baseapplication.ui.fragments.search.SearchFragment
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteDetailViewModel
import com.smarthub.baseapplication.viewmodels.MainViewModel

class  DashboardActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: SiteDetailViewModel
    lateinit var navController : NavController

    private lateinit var mainViewModel: MainViewModel
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           binding = ActivityMainBinding.inflate(layoutInflater)
           setContentView(binding.root)
           mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
           viewModel = ViewModelProvider(this)[SiteDetailViewModel::class.java]
           initializeCustomActionBar()
           val navView: BottomNavigationView = binding.navView

           navController = findNavController(R.id.nav_host_fragment_activity_main)
           navView.setupWithNavController(navController)
           navView.itemIconTintList = null
           binding.navView.setOnItemSelectedListener { item ->
               if (navController.backQueue.size > 2) navController.popBackStack(
                   R.id.navigation_home,
                   false
               )
               item.isChecked = true
               if (item.itemId != R.id.navigation_home) navController.navigate(item.itemId)

               true
           }

           binding.navView.setOnItemReselectedListener(NavigationBarView.OnItemReselectedListener { item ->
               navController.popBackStack(
                   item.itemId,
                   false
               )
           })

//           navController.addOnDestinationChangedListener { navController, navDestination, bundle ->
//               Log.d("status", "onMenuOpened:" + navDestination.navigatorName)
//           }
       }

    override fun onBackPressed() {
        val navFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
        val currentFragment = navFragment!!.childFragmentManager.primaryNavigationFragment
        if (currentFragment is SearchFragment && currentFragment.searchResultAdapter.list.size>0){
            currentFragment.clearResult()
        }else
            super.onBackPressed()

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