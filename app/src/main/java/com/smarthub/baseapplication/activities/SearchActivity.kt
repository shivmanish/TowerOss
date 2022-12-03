package com.smarthub.baseapplication.activities

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.SearchActivityBinding
import com.smarthub.baseapplication.viewmodels.SearchActivityViewModel

class SearchActivity : BaseActivity() {
    var navController:NavController?=null
    private var dataBinding: SearchActivityBinding? = null

    private lateinit var mViewModel: SearchActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = this.supportActionBar
        actionBar?.hide()
        dataBinding = SearchActivityBinding.inflate(layoutInflater)
        mViewModel = ViewModelProvider(this)[SearchActivityViewModel::class.java]
        setContentView(dataBinding?.root)
        navController=Navigation.findNavController(dataBinding?.root?.findViewById(R.id.nav_host)!!)


    }

}