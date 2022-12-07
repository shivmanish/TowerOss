package com.smarthub.baseapplication.ui.fragments.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.LoginActivity
import com.smarthub.baseapplication.databinding.RegistrationFirstStepBinding
import com.smarthub.baseapplication.databinding.RegistrationSecondStepBinding
import com.smarthub.baseapplication.ui.adapter.spinner.CustomRegistrationArrayAdapter
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationSecondStep.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class RegistrationSecondStep : Fragment() {
    lateinit var registrationSecondStepBinding: RegistrationSecondStepBinding
    lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        registrationSecondStepBinding = RegistrationSecondStepBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        return registrationSecondStepBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationSecondStepBinding.jobRole.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(registrationSecondStepBinding.jobRole.text.toString().length>=3){
                    registrationSecondStepBinding.jobRoleRoot.setEndIconDrawable(R.drawable.check_textview)
                }
                else
                    registrationSecondStepBinding.jobRoleRoot.setEndIconDrawable(R.color.transparent)
            }
            override fun afterTextChanged(s: Editable) {
                if(registrationSecondStepBinding.jobRole.text.toString().length>=3){
                    registrationSecondStepBinding.jobRoleRoot.setEndIconDrawable(R.drawable.check_textview)
                }
            }
        })
        registrationSecondStepBinding.department.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(registrationSecondStepBinding.department.text.toString().length>=3){
                    registrationSecondStepBinding.departmentRoot.setEndIconDrawable(R.drawable.check_textview)
                }
                else
                    registrationSecondStepBinding.departmentRoot.setEndIconDrawable(R.color.transparent)
            }
            override fun afterTextChanged(s: Editable) {
                if(registrationSecondStepBinding.department.text.toString().length>=3){
                    registrationSecondStepBinding.departmentRoot.setEndIconDrawable(R.drawable.check_textview)
                }


            }
        })
        registrationSecondStepBinding.roleGeography.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(registrationSecondStepBinding.roleGeography.text.toString().length>=3){
                    registrationSecondStepBinding.roleGeographyRoot.setEndIconDrawable(R.drawable.check_textview)
                }
                else
                    registrationSecondStepBinding.roleGeographyRoot.setEndIconDrawable(R.color.transparent)
            }
            override fun afterTextChanged(s: Editable) {
                if(registrationSecondStepBinding.roleGeography.text.toString().length>=3){
                    registrationSecondStepBinding.roleGeographyRoot.setEndIconDrawable(R.drawable.check_textview)
                }
            }
        })

        val loginButton = view.findViewById<View>(R.id.text_register)
        loginButton.setOnClickListener { view ->
            Utils.hideKeyboard(requireContext(),view)
            activity?.let{
                val intent = Intent (it, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                it.startActivity(intent)
            }
        }

        view.findViewById<View>(R.id.next).setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            loginViewModel.registerData.rolestxt = registrationSecondStepBinding.jobRole.text.toString()
            loginViewModel.registerData.departmenttxt = registrationSecondStepBinding.department.text.toString()
            loginViewModel.registerData.rolegeographytxt = registrationSecondStepBinding.roleGeography.text.toString()
            findNavController().navigate(RegistrationSecondStepDirections.actionRegistrationSecondStepToRegistrationThirdStep())
        }
    }

}


