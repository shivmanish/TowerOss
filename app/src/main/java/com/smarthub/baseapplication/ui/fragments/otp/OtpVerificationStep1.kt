package com.smarthub.baseapplication.ui.fragments.otp

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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OtpVerificationStep1FragmentBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.otp.GetOtpResponse
import com.smarthub.baseapplication.model.otp.UserOTPGet
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [OtpVerificationStep1.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtpVerificationStep1 : Fragment() {

    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    lateinit var binding : OtpVerificationStep1FragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = OtpVerificationStep1FragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        binding.emailIdRoot.tag = false
        binding.moNoLayout.tag = false
        binding.sendOtp.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            if (binding.emailIdRoot.tag==false) {
                binding.emailIdRoot.isErrorEnabled = true
                if (binding.emailId.text.toString().isEmpty())
                    binding.emailIdRoot.error = "Field should not be empty"
                else
                    binding.emailIdRoot.error = "Please Enter Valid Email Id"
                return@setOnClickListener
            }
            if (!progressDialog.isShowing)
                progressDialog.show()
            loginViewModel?.getPhoneOtp(UserOTPGet(binding.moNoEdit.text?.toString(),binding.emailId.text.toString()))
        }

        binding.emailId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if(binding.emailId.text.toString().isNotEmpty()){
                    if (Utils.isValidEmail(binding.emailId.text.toString())){
                        binding.emailIdRoot.tag =true
                        binding.emailIdRoot.isErrorEnabled = false
                    }
                    else{
                        binding.emailIdRoot.tag =false
                    }

                }
                else {
                    binding.emailIdRoot.setEndIconDrawable(0)
                    binding.emailIdRoot.tag=false
                }
            }
        })

        binding.moNoEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.moNoEdit.text.toString().length==10) {
                    Utils.hideKeyboard(requireContext(), binding.moNoEdit)
                    binding.phoneNumLayout.isErrorEnabled = false
                    binding.sendOtp.isClickable=true
                    binding.sendOtp.setImageResource(R.drawable.mo_no_next_outline_white)

                }else{
                    binding.sendOtp.isClickable=false
                    binding.sendOtp.setImageResource(R.drawable.mo_no_next_outline)
                }
            }
        })
        if (loginViewModel?.getOtpResponse?.hasActiveObservers() == true){
            loginViewModel?.getOtpResponse?.removeObservers(viewLifecycleOwner)
        }
        loginViewModel?.getOtpResponse?.observe(viewLifecycleOwner) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()

            if (it?.data != null && it.data.sucesss == true){
                Log.d("status","getPhoneOtp: ${it.data}")
                findNavController().navigate(OtpVerificationStep1Directions.actionOtpVerificationStep1ToOtpVerificationStep2(
                    binding.moNoEdit.text.toString(),binding.emailId.text.toString()))

            }else{
                Log.d("status", AppConstants.GENERIC_ERROR)
//                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
                Toast.makeText(requireActivity(), "Email Id or Mobile Number is Invalid", Toast.LENGTH_LONG).show()
//                enableErrorText()
            }
        }
    }

    override fun onDestroyView() {
        if (loginViewModel?.getOtpResponse?.hasActiveObservers() == true){
            loginViewModel?.getOtpResponse?.removeObservers(viewLifecycleOwner)
        }
        super.onDestroyView()
    }

    private fun enableErrorText(){
        binding.phoneNumLayout.error = "enter valid mo no"
    }

}


