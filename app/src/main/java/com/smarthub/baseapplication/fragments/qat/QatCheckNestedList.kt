package com.smarthub.baseapplication.fragments.qat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.adapter.qat.QatTitleAdapter
import com.smarthub.baseapplication.databinding.QatNestedListBinding
import com.smarthub.baseapplication.listeners.QatItemListener

class QatCheckNestedList() : Fragment(), QatItemListener {

    var binding : QatNestedListBinding ?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.qat_nested_list, container, false)
        binding = QatNestedListBinding.bind(view)
        binding?.list?.adapter = QatTitleAdapter(this@QatCheckNestedList)
        return view
       }
        fun addFragment(fragment: Fragment?) {
        val backStateName: String = requireActivity().supportFragmentManager.javaClass.name
        val manager = requireActivity().supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.replace(R.id.fragmentContainerView, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }

    override fun itemClicked() {

    }

}

