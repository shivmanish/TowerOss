package com.smarthub.baseapplication.ui.fragments.forgot_password

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
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ForgotPassStep1FragmentBinding
import com.smarthub.baseapplication.listeners.DrawableClickListener
import com.smarthub.baseapplication.model.otp.UserOTPGet
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class ForgotPassStep1 : Fragment() {

    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    lateinit var binding : ForgotPassStep1FragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ForgotPassStep1FragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)
        binding.moNoEdit.setTag(false)
        binding.back?.setOnClickListener {view->
            Utils.hideKeyboard(requireContext(),view)
            activity?.let{
                it?.onBackPressed()
            }
        }

        binding.moNoEdit.setOnTouchListener(object : DrawableClickListener(DRAWABLE_RIGHT) {
            override fun onDrawableClick(): Boolean {
                Log.d("status"," DRAWABLE_RIGHT : moNoEdit")
                Utils.hideKeyboard(requireContext(),binding.moNoEdit)
                binding.moNoEdit.clearFocus()
                if (binding.moNoEdit.tag as Boolean){
                    if (!progressDialog.isShowing)
                        progressDialog.show()
                    loginViewModel?.getPhoneOtp(UserOTPGet(binding.moNoEdit.text?.toString()))
                }
                return false
            }
        })

        binding.moNoEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.moNoEdit.text.toString().isNotEmpty() && binding.moNoEdit.text.toString().length>=10) {
                    Utils.hideKeyboard(requireContext(), binding.moNoEdit)
                    binding.moNoEdit.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.mo_no_next_outline_white,0)
                    binding.moNoEdit.setTag(true)
                }else{
                    binding.moNoEdit.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.mo_no_next_outline,0)
                    binding.moNoEdit.setTag(false)
                }
            }
        })

        loginViewModel?.getOtpResponse?.observe(requireActivity()) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()

            if (it?.data != null && it.data.sucesss == true){
                Log.d("status","getOtpResponse ${it.data}")
                activity?.let{
                    findNavController().navigate(
                        ForgotPassStep1Directions.actionForgotPassStep1ToForgotPassStep2(binding.moNoEdit.text?.toString()!!)
                    )
                }
            }else{
                Log.d("status","${AppConstants.GENERIC_ERROR}")
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
                enableErrorText()
            }
        }
    }

    private fun enableErrorText(){
        binding.userMailLayout?.error = "enter valid mo no"
    }

}


