package com.smarthub.baseapplication.activities

import android.graphics.Path.Direction
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchActivityBinding
import com.smarthub.baseapplication.ui.fragments.sitedetail.SiteDetailFragment
import com.smarthub.baseapplication.viewmodels.SearchActivityViewModel

class SearchActivity : BaseActivity() {
    private var dataBinding: SearchActivityBinding? = null
    private lateinit var mViewModel: SearchActivityViewModel

    var navController:NavController?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = this.supportActionBar
        actionBar?.hide()
        dataBinding = SearchActivityBinding.inflate(layoutInflater)

        mViewModel = ViewModelProvider(this)[SearchActivityViewModel::class.java]
        setContentView(dataBinding?.root)
        navController=Navigation.findNavController(dataBinding?.root?.findViewById(R.id.nav_host)!!)


    }
       fun  navigationFragment(id:Direction){
       //  navController?.navigate(id)
       }


}