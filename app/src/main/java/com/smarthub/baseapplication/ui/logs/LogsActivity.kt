package com.smarthub.baseapplication.ui.logs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smarthub.baseapplication.databinding.ActivityLogsBinding

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