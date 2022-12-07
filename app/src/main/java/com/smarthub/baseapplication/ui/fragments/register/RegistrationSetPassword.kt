package com.smarthub.baseapplication.ui.fragments.register

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.databinding.RegistrationSetPassBinding
import com.smarthub.baseapplication.databinding.RegistrationSetPasswordBinding
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class RegistrationSetPassword : Fragment() {

    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    lateinit var binding : RegistrationSetPasswordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = RegistrationSetPasswordBinding.inflate(inflater)
        return binding.root
    }
    var phone = "1234567890"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments!=null && arguments?.containsKey("phone") == true){
            phone = arguments?.getString("phone")!!
            Log.d("status","onViewCreated phone:$phone")
            loginViewModel?.getRegisterOtp(phone)
        }
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        binding.confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.moNoEdit.text.toString().isNotEmpty() && binding.moNoEdit.text.toString() == binding.confirmPassword.text.toString()) {
                    Utils.hideKeyboard(requireContext(), binding.moNoEdit)
                    binding.submitPass.isClickable=true
                    binding.submitPass.alpha = 1.0f
                }else{
                    binding.submitPass.alpha = 0.3f
                }
            }
        })

        if (loginViewModel?.getPassResponse?.hasActiveObservers() == true)
            loginViewModel?.getPassResponse?.removeObservers(viewLifecycleOwner)
        loginViewModel?.getPassResponse?.observe(requireActivity()) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()

            if (it?.data != null && it.data.success == true){
                Log.d("status","observe getOtpResponse: ${it.data}")
                findNavController().navigate(RegistrationSetPasswordDirections.actionRegistrationSetPasswordToRegistrationSuccessfull())
            }else  if (it?.data != null){
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
                enableErrorText(it?.data?.error)
            } else{
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
                enableErrorText()
            }
        }
        binding.submitPass.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            if (!progressDialog.isShowing)
                progressDialog.show()
            loginViewModel?.registerPassword(phone,binding.moNoEdit.text.toString())
        }
    }

    private fun enableErrorText(){
        binding.passwordLayout.error = "enter valid mo no"
    }

    private fun enableErrorText(error:String?){
        binding.confirmPassLayout.error = "$error"
    }
}


