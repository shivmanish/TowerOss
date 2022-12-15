package com.smarthub.baseapplication.ui.logs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityFireExtinguisherDetailsBinding
import com.smarthub.baseapplication.databinding.ActivityLogsBinding
import com.smarthub.baseapplication.ui.utilites.adapter.BatteryViewpagerAdapter
import com.smarthub.baseapplication.ui.utilites.editdialouge.CommonBottomSheetDialog
import com.smarthub.baseapplication.ui.utilites.fragment.FireExtinguisherFragment

class LogsActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityLogsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
    }
    fun initview(){
        binding.rv.adapter = LogsViewRecyclerAdapter()
    }
}