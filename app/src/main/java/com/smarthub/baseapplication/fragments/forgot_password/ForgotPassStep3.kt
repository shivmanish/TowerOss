package com.smarthub.baseapplication.fragments.forgot_password

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.activities.LoginActivity
import com.smarthub.baseapplication.R


/**
 * A simple [Fragment] subclass.
 * Use the [ForgotPassStep3.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class ForgotPassStep3 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.forgot_pass_step3_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val loginButton = view.findViewById<View>(R.id.text_register)
//        loginButton.setOnClickListener {
//            activity?.let{
//                val intent = Intent (it, LoginActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                it.startActivity(intent)
//            }
//        }

//        val regFragment2 = ForgotPassStep4()
//        view.findViewById<View>(R.id.next_layout).setOnClickListener {
//            activity?.let{
//                addFragment(regFragment2)
//            }
//        }
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


