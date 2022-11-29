package com.smarthub.baseapplication.ui.fragments.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.TabNameItemBinding
import com.smarthub.baseapplication.databinding.TaskSecondFragmnetBinding
import com.smarthub.baseapplication.databinding.TaskThirdFragmnetBinding
import com.smarthub.baseapplication.ui.basic_info.BasicInfoPagerAdapter
import com.smarthub.baseapplication.ui.fragments.task.adapter.TaskViewpagerAdapter
import kotlinx.android.synthetic.main.new_customer_detail_fragment.*
import kotlinx.android.synthetic.main.qat_punch_point_item.view.*
import kotlinx.android.synthetic.main.tab_name_item.view.*

class TaskThirdFragment:Fragment() {
    lateinit var binding:TaskThirdFragmnetBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TaskThirdFragmnetBinding.inflate(inflater)
        setview()
        return binding.root
    }

    private fun setview() {

    }

}