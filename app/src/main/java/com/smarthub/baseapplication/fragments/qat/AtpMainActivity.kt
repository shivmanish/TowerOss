package com.smarthub.baseapplication.fragments.qat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.adapter.AtpListAdapter
import com.smarthub.baseapplication.adapter.history.AtpMainListAdapter
import com.smarthub.baseapplication.databinding.AtpMainScreenBinding
import com.smarthub.baseapplication.listeners.QatProfileListener

class AtpMainActivity : AppCompatActivity(), QatProfileListener {

    var binding: AtpMainScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AtpMainScreenBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        init()
    }

    private fun init() {
        binding?.atpList?.adapter = AtpMainListAdapter(this@AtpMainActivity)
    }

    override fun itemClicked() {
        var fragment = AtpMainFragment()
        addFragment(fragment)
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
            transaction.add(R.id.container, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }

}