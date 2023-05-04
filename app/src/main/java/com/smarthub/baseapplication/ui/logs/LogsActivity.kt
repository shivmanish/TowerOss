package com.smarthub.baseapplication.ui.logs

import android.os.Bundle
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.databinding.ActivityLogsBinding

class LogsActivity : BaseActivity() {
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