package com.smarthub.baseapplication.ui.fragments.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.utils.Utils


/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationSuccessfull.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class RegistrationSuccessfull : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.registration_successfull, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginButton = view.findViewById<View>(R.id.text_login)
        loginButton.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            activity?.let{
                findNavController().popBackStack(R.id.loginFragment,false)
            }
        }

//        view.findViewById<View>(R.id.back).setOnClickListener {
//            Utils.hideKeyboard(requireContext(),it)
//            activity?.let{
//                it.onBackPressed()
//            }
//        }
    }

}


