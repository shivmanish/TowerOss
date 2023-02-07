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
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Category
import com.smarthub.baseapplication.model.siteInfo.qat.qat_main.Subitem
import com.smarthub.baseapplication.ui.adapter.qat.QatSubTitleAdapter
import com.smarthub.baseapplication.ui.fragments.BaseFragment

class QatNestedItemFragment(var data: Category?) : BaseFragment(),
    QatSubTitleAdapter.QatSubTitleAdapterListener {

    var binding : QatNestedListFragmentBinding ?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.qat_nested_list_fragment, container, false)
        binding = QatNestedListFragmentBinding.bind(view)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(data?.item!=null)
            binding?.list?.adapter = QatTitleAdapter(this@QatNestedItemFragment,data?.item!!)
    }
    override fun itemClicked(item : Subitem) {
        QatDetailsActivity.data = item
        Intent(requireContext(),QatDetailsActivity::class.java).apply {
            startActivity(this)
        }

    }

}


