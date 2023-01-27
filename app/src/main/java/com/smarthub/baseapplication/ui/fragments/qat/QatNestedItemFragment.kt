package com.smarthub.baseapplication.ui.fragments.qat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.ui.adapter.qat.QatTitleAdapter
import com.smarthub.baseapplication.databinding.QatNestedListBinding
import com.smarthub.baseapplication.databinding.QatNestedListFragmentBinding
import com.smarthub.baseapplication.listeners.QatItemListener
import com.smarthub.baseapplication.model.siteInfo.qat.QatCardItem
import com.smarthub.baseapplication.model.siteInfo.qat.QatTemplateModel

class QatNestedItemFragment(var data: QatTemplateModel?) : Fragment(), QatItemListener {

    var binding : QatNestedListFragmentBinding ?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.qat_nested_list_fragment, container, false)
        binding = QatNestedListFragmentBinding.bind(view)
        binding?.list?.adapter = QatTitleAdapter(this@QatNestedItemFragment)
        return view

    }
    override fun itemClicked() :String {
        QatDetailsActivity.data = data
        Intent(requireContext(),QatDetailsActivity::class.java).apply {
            startActivity(this)
        }
        return ""

    }

}


