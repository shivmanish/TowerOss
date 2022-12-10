package com.smarthub.baseapplication.ui.fragments.register

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.LoginActivity
import com.smarthub.baseapplication.databinding.RegistrationSecondStepBinding
import com.smarthub.baseapplication.databinding.RegistrationThirdStepBinding
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


class RegistrationThirdStep : Fragment() {
    lateinit var progressDialog: ProgressDialog
    lateinit var registrationViewModel: LoginViewModel
    lateinit var registrationThirdStepBinding: RegistrationThirdStepBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        registrationThirdStepBinding= RegistrationThirdStepBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.registration_third_step, container, false)
        registrationViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        return registrationThirdStepBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationThirdStepBinding.managerNameRoot.tag=false
        registrationThirdStepBinding.emailIdRoot.tag==false
        registrationThirdStepBinding.moNo.tag==false
        registrationThirdStepBinding.managerName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if(registrationThirdStepBinding.managerName.text.toString().isNotEmpty()){
                    registrationThirdStepBinding.managerNameRoot.setEndIconDrawable(R.drawable.check_textview)
                    registrationThirdStepBinding.managerNameRoot.tag=true
                    registrationThirdStepBinding.managerNameRoot.isErrorEnabled = false
                }
                else {
                    registrationThirdStepBinding.managerNameRoot.setEndIconDrawable(R.color.transparent)
                    registrationThirdStepBinding.managerNameRoot.tag=false
                }


            }
        })
        registrationThirdStepBinding.emailId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {  }
            override fun afterTextChanged(s: Editable) {
                if(registrationThirdStepBinding.emailId.text.toString().isNotEmpty()){
                    registrationThirdStepBinding.emailIdRoot.setEndIconDrawable(R.drawable.check_textview)
                    registrationThirdStepBinding.emailIdRoot.tag=true
                    registrationThirdStepBinding.emailIdRoot.isErrorEnabled = false
                }
                else {
                    registrationThirdStepBinding.emailIdRoot.setEndIconDrawable(R.color.transparent)
                    registrationThirdStepBinding.emailIdRoot.tag=false
                }


            }
        })
        registrationThirdStepBinding.moNo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable) {
                if(registrationThirdStepBinding.moNo.text.toString().length==10){
                    Utils.hideKeyboard(requireContext(), registrationThirdStepBinding.moNo)
                    registrationThirdStepBinding.moNoRoot.setEndIconDrawable(R.drawable.check_textview)
                    registrationThirdStepBinding.moNoRoot.tag=true
                    registrationThirdStepBinding.moNoRoot.isErrorEnabled = false
                }
                else {
                    registrationThirdStepBinding.moNoRoot.setEndIconDrawable(R.color.transparent)
                    registrationThirdStepBinding.moNoRoot.tag=false
                }
            }
        })

        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        registrationThirdStepBinding.textLogin.setOnClickListener { view ->
            Utils.hideKeyboard(requireContext(), view)
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                it.startActivity(intent)
            }
        }
        registrationThirdStepBinding.next.setOnClickListener {
            Utils.hideKeyboard(requireContext(), it)
            if (registrationThirdStepBinding.managerNameRoot.tag==false) {
                registrationThirdStepBinding.managerNameRoot.isErrorEnabled = true
                registrationThirdStepBinding.managerNameRoot.error = "Enter job role"
                return@setOnClickListener
            }
            if (registrationThirdStepBinding.emailIdRoot.tag==false) {
                registrationThirdStepBinding.emailIdRoot.isErrorEnabled = true
                registrationThirdStepBinding.emailIdRoot.error = "Enter job role"
                return@setOnClickListener
            }
            if (registrationThirdStepBinding.moNoRoot.tag==false) {
                registrationThirdStepBinding.moNoRoot.isErrorEnabled = true
                registrationThirdStepBinding.moNoRoot.error = "Enter job role"
                return@setOnClickListener
            }

            registrationViewModel.registerData.managername = registrationThirdStepBinding.managerName.text.toString()
            registrationViewModel.registerData.manageremail = registrationThirdStepBinding.emailId.text.toString()
            registrationViewModel.registerData.managerphone = registrationThirdStepBinding.moNo.text.toString()


            findNavController().navigate(RegistrationThirdStepDirections.actionRegistrationThirdStepToRegistrationSetPassword())

        }

    }

}


