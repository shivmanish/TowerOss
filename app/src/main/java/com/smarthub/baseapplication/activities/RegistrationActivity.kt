package com.smarthub.baseapplication.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.fragments.RegFragment_1

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        val regFragment1 = RegFragment_1()
        this.supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView,regFragment1).commit()


    }
}