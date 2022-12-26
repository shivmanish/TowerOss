package com.smarthub.baseapplication.ui.fragments.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.ui.fragments.task.TaskSearchTabFragment
import com.smarthub.baseapplication.ui.fragments.task.TaskSecondFragment
import com.smarthub.baseapplication.ui.fragments.task.TaskThirdFragment

open class DemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
      //  setFragment(TaskThirdFragment())
        setFragment(TaskSearchTabFragment())
    }

    private fun setFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.placeholder, fragment)
        fragmentTransaction.commit()
    }
}