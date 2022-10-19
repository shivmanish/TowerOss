package com.smarthub.baseapplication.ui.fragments.forgot_password

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.utils.Utils

class ForgotPassStep6 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.forgot_pass_step6_fragment, container, false)

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

        val regFragment2 = ForgotPassStep7()
        view.findViewById<View>(R.id.next_layout).setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
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


