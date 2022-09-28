package com.smarthub.baseapplication.fragments.otp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.fragments.forgot_password.ForgotPassStep2


/**
 * A simple [Fragment] subclass.
 * Use the [OtpVerificationStep2.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class OtpVerificationStep2 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.otp_verification_step2_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.back).setOnClickListener {
            activity?.let{
                it.onBackPressed()
            }
        }

        val regFragment2 = OtpVerificationStep3()
        view.findViewById<View>(R.id.next_layout).setOnClickListener {
            activity?.let{
               addFragment(regFragment2)
            }
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


