package com.smarthub.baseapplication.ui.fragments.qat

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityQatBinding
import com.smarthub.baseapplication.databinding.ActivityQatDetailsBinding
import com.smarthub.baseapplication.ui.fragments.qat.adapter.PageAdapterQat

class QatDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityQatDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQatDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    fun init() {


    }

}