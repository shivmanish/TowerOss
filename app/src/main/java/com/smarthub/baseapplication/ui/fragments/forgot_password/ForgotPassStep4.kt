package com.smarthub.baseapplication.ui.fragments.forgot_password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ForgotPassStep4FragmentBinding

class ForgotPassStep4 : Fragment() {

    lateinit var binding : ForgotPassStep4FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = ForgotPassStep4FragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textLogin.setOnClickListener {
            findNavController().popBackStack(R.id.loginFragment,true)
        }
    }

}


