package com.smarthub.baseapplication.fragments.qat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.adapter.AtpListAdapter
import com.smarthub.baseapplication.adapter.qat.QatTitleAdapter
import com.smarthub.baseapplication.databinding.AtpMainFragmentBinding
import com.smarthub.baseapplication.databinding.AtpMainScreenBinding
import com.smarthub.baseapplication.databinding.QatNestedListBinding
import com.smarthub.baseapplication.listeners.QatProfileListener

class AtpMainFragment : Fragment(), QatProfileListener {

    var binding: AtpMainFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.atp_main_fragment, container, false)
        binding = AtpMainFragmentBinding.bind(view)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding?.atpList?.adapter = AtpListAdapter(this@AtpMainFragment)
    }

    override fun itemClicked() {
        var fragment = QatCheckNestedList()
        (requireActivity() as AtpMainActivity).addFragment(fragment)
    }

}