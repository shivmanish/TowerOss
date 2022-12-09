package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.databinding.TaskActivityBinding

class TaskActivity : BaseActivity() {
    private var dataBinding: TaskActivityBinding? = null

    var navController:NavController?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val actionBar: ActionBar? = this.supportActionBar
        actionBar?.hide()
        dataBinding = TaskActivityBinding.inflate(layoutInflater)
        setContentView(dataBinding?.root)
        navController=Navigation.findNavController(dataBinding?.root?.findViewById(R.id.nav_host)!!)

        dataBinding?.mainActionBar?.setOnClickListener {
            onBackPressed()
        }

    }

}