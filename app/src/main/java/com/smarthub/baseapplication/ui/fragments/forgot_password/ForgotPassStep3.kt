package com.smarthub.baseapplication.ui.fragments.forgot_password

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ForgotPassStep3FragmentBinding
import com.smarthub.baseapplication.model.otp.UserOTPGet
import com.smarthub.baseapplication.model.otp.UserPasswordGet
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class ForgotPassStep3 : Fragment() {

    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    lateinit var binding : ForgotPassStep3FragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ForgotPassStep3FragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var acessToken: String = ""
        var refresToken: String = ""
        var phoneNumber: String = ""
        if (requireArguments().containsKey("access")) {
            try {
                Log.d("status","access key :" + requireArguments().getString("access"))
                acessToken= requireArguments().getString("access").toString()
            } catch (e: Exception) {
                Log.d("status","error e :${e.localizedMessage}")
            }
        }
        if (requireArguments().containsKey("refresh")) {
            try {
                Log.d("status","refresh key :" + requireArguments().getString("refresh"))
                refresToken= requireArguments().getString("refresh").toString()
            } catch (e: Exception) {
                Log.d("status","error e :${e.localizedMessage}")
            }
        }
        if (requireArguments().containsKey("phone")) {
            try {
                Log.d("status","phone key :" + requireArguments().getString("phone"))
                phoneNumber= requireArguments().getString("phone").toString()
            } catch (e: Exception) {
                Log.d("status","error e :${e.localizedMessage}")
            }
        }
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (Utils.validatePass(binding.password.text.toString()) && binding.password.text.toString() == binding.confirmPassword.text.toString()) {
                    Utils.hideKeyboard(requireContext(), binding.password)
                    binding.submitPass.isEnabled=true
                    binding.submitPass.alpha = 1.0f
                }else{
                    binding.submitPass.alpha = 0.3f
                    binding.submitPass.isEnabled=false
                }
            }
        })
        binding.confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (Utils.validatePass(binding.password.text.toString()) && binding.password.text.toString() == binding.confirmPassword.text.toString()) {
                    Utils.hideKeyboard(requireContext(), binding.password)
                    binding.submitPass.isEnabled=true
                    binding.submitPass.alpha = 1.0f
                }else{
                    binding.submitPass.alpha = 0.3f
                    binding.submitPass.isEnabled=false
                }
            }
        })
        if (loginViewModel?.getPassResponse?.hasActiveObservers() == true){
            loginViewModel?.getPassResponse?.removeObservers(viewLifecycleOwner)
        }
        loginViewModel?.getPassResponse?.observe(viewLifecycleOwner) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()

            if (it?.data != null && it.data.success == true){
                Log.d("status","observe getOtpResponse: ${it.data}")
                findNavController().navigate(
                    ForgotPassStep3Directions.actionForgotPassStep3ToForgotPassStep4()
                )
            }else  if (it?.data != null){
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            } else{
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
            }
        }
        binding.submitPass.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            if (!progressDialog.isShowing)
                progressDialog.show()
            loginViewModel?.changePassword(phoneNumber,binding.confirmPassword.text.toString())
        }
    }

}


