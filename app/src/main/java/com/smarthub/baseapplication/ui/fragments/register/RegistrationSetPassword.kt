package com.smarthub.baseapplication.ui.fragments.register

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.databinding.RegistrationSetPassBinding
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class RegistrationSetPassword : Fragment() {

    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    lateinit var binding : RegistrationSetPassBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RegistrationSetPassBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        binding.confirmPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.moNoEdit.text.toString().isNotEmpty() && binding.moNoEdit.text.toString() == binding.confirmPass.text.toString()) {
                    Utils.hideKeyboard(requireContext(), binding.moNoEdit)
                    binding.signWithPhone.alpha = 1.0f
                    binding.signWithPhone.isEnabled = true
                }else{
                    binding.signWithPhone.alpha = 0.3f
                    binding.signWithPhone.isEnabled = false
                }
            }
        })

        binding.signWithPhone.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            if (!progressDialog.isShowing)
                progressDialog.show()
            loginViewModel?.registerData?.password = binding.moNoEdit.text.toString()
            loginViewModel?.registerUser()
        }

        if (loginViewModel?.getRegister()?.hasActiveObservers() == true)
            loginViewModel?.getRegister()?.removeObservers(viewLifecycleOwner)
        loginViewModel?.getRegister()?.observe(viewLifecycleOwner){
            if (progressDialog.isShowing){
                progressDialog.dismiss()
            }
            if (it!=null && it.status == "success"){
                findNavController().navigate(RegistrationSetPasswordDirections.actionRegistrationSetPasswordToRegistrationOtpFragment())
            }
        }
    }

    private fun enableErrorText(){
        binding.userMailLayout.error = "enter valid mo no"
    }

    private fun enableErrorText(error:String?){
        binding.userMailLayout.error = "$error"
    }
}


