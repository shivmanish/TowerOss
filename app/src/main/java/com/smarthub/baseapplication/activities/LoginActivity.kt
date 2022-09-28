package com.smarthub.baseapplication.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.fragments.forgot_password.ForgotPassStep1
import com.smarthub.baseapplication.fragments.login.LoginSecondStep
import com.smarthub.baseapplication.fragments.otp.OtpVerificationStep1
import com.smarthub.baseapplication.fragments.register.RegistrationFirstStep

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<View>(R.id.login)
        login.setOnClickListener {
            val regFragment1 = LoginSecondStep()
            addFragment(regFragment1)
        }

        val textRegister = findViewById<View>(R.id.text_register)
        textRegister.setOnClickListener {
            val regFragment1 = RegistrationFirstStep()
            addFragment(regFragment1)
        }

        val forgoPassword = findViewById<View>(R.id.forgot_password)
        forgoPassword.setOnClickListener {
            val regFragment1 = ForgotPassStep1()
            addFragment(regFragment1)
        }

        val signWithPhone = findViewById<View>(R.id.sign_with_phone)
        signWithPhone.setOnClickListener {
            val regFragment1 = OtpVerificationStep1()
            addFragment(regFragment1)
        }
    }

    fun addFragment(fragment: Fragment?) {
        val backStateName: String = supportFragmentManager.javaClass.name
        val manager = supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.replace(R.id.fragmentContainerView, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount === 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}