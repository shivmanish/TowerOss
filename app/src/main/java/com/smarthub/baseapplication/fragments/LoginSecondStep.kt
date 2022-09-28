package com.smarthub.baseapplication.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.activities.LoginActivity
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.MainActivity


/**
 * A simple [Fragment] subclass.
 * Use the [LoginSecondStep.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class LoginSecondStep : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.loging_second_step, container, false)

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val login = view.findViewById<View>(R.id.login)
        login.setOnClickListener {
            val intent = Intent (requireActivity(), MainActivity::class.java)
            requireActivity().startActivity(intent)
        }

        val textRegister = view.findViewById<View>(R.id.text_register)
        textRegister.setOnClickListener {
            val regFragment1 = RegistrationFirstStep()
            addFragment(regFragment1)
        }

        val forgoPassword = view.findViewById<View>(R.id.forgot_password)
        forgoPassword.setOnClickListener {
//            val regFragment1 = LoginSecondStep()
//            addFragment(regFragment1)
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


