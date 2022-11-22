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
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)
        binding.signWithPhone.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            activity?.let{
                findNavController().navigate(ForgotPassStep3Directions.actionForgotPassStep3ToForgotPassStep4())
            }
        }

        binding.confirmPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.moNoEdit.text.toString().isNotEmpty() && binding.moNoEdit.text.toString() == binding.confirmPass.text.toString()) {
                    Utils.hideKeyboard(requireContext(), binding.moNoEdit)
                    binding.signWithPhone.visibility = View.VISIBLE
                    binding.signWithPhoneDisable.visibility = View.GONE
                }else{
                    binding.signWithPhoneDisable.visibility = View.VISIBLE
                    binding.signWithPhone.visibility = View.GONE
                }
            }
        })
        loginViewModel?.getPassResponse?.observe(requireActivity()) {
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
                enableErrorText(it?.data?.error)
            } else{
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
                enableErrorText()
            }
        }
        binding.signWithPhone.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            if (!progressDialog.isShowing)
                progressDialog.show()
            loginViewModel?.changePassword(binding.moNoEdit.text.toString())
        }
    }

    private fun enableErrorText(){
        binding.userMailLayout.error = "enter valid mo no"
    }

    private fun enableErrorText(error:String?){
        binding.userMailLayout.error = "$error"
    }
}


