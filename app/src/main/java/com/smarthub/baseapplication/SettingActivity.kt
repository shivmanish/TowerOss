package com.smarthub.baseapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.smarthub.baseapplication.databinding.ActivityDashboardBinding
import com.smarthub.baseapplication.model.LangModel

class SettingActivity : AppCompatActivity() {

    private var dataBinding : ActivityDashboardBinding ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dataBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dataBinding?.root)
        initViews()
    }

    //    initialize all view
    fun initViews(){
    }
}