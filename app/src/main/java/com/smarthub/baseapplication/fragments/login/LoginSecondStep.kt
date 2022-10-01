package com.smarthub.baseapplication.fragments.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.fragments.forgot_password.ForgotPassStep1
import com.smarthub.baseapplication.fragments.otp.OtpVerificationStep1
import com.smarthub.baseapplication.fragments.register.RegistrationFirstStep
import com.smarthub.baseapplication.utils.Utility


class LoginSecondStep : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.loging_second_step, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val login = view.findViewById<View>(R.id.login)
        login.setOnClickListener {
            Utility.hideKeyboard(requireContext(),it)
            val intent = Intent (requireActivity(), DashboardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            requireActivity().startActivity(intent)
        }

        val textRegister = view.findViewById<View>(R.id.text_register)
        textRegister.setOnClickListener {
            Utility.hideKeyboard(requireContext(),it)
            val regFragment1 = RegistrationFirstStep()
            addFragment(regFragment1)
        }

        val forgoPassword = view.findViewById<View>(R.id.forgot_password)
        forgoPassword.setOnClickListener {
            Utility.hideKeyboard(requireContext(),it)
            val regFragment1 = ForgotPassStep1()
            addFragment(regFragment1)
        }

        val signWithPhone = view.findViewById<View>(R.id.sign_with_phone)
        signWithPhone.setOnClickListener {
            Utility.hideKeyboard(requireContext(),it)
            val regFragment1 = OtpVerificationStep1()
            addFragment(regFragment1)
        }
    }

    fun addFragment(fragment: Fragment?) {
        val backStateName: String = requireActivity().supportFragmentManager.javaClass.name
        val manager = requireActivity().supportFragmentManager
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

}


