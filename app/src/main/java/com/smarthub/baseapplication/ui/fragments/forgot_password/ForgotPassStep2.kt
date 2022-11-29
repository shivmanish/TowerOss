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
import com.smarthub.baseapplication.databinding.ForgotPassStep2FragmentBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.listeners.DrawableClickListener
import com.smarthub.baseapplication.model.otp.UserOTPGet
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class ForgotPassStep2 : Fragment() {

    lateinit var binding : ForgotPassStep2FragmentBinding
    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ForgotPassStep2FragmentBinding.inflate(inflater)
        return binding.root
    }

    private fun enableErrorText(){
        binding.userMailLayout.error = "enter valid password"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        if (requireArguments().containsKey("phone")) {
            try {
                Log.d("status","key :" + requireArguments().getString("phone"))
                binding.moNoEdit.setText(requireArguments().getString("phone"))
            } catch (e: Exception) {
                Log.d("status","error e :${e.localizedMessage}")
            }
        }
        binding.moNoEdit.setOnTouchListener(object : DrawableClickListener(DRAWABLE_RIGHT) {
            override fun onDrawableClick(): Boolean {
                Log.d("status"," DRAWABLE_RIGHT : moNoEdit")
                Utils.hideKeyboard(requireContext(),binding.moNoEdit)
                binding.moNoEdit.clearFocus()
                findNavController().popBackStack()
                return false
            }
        })


        binding.nextLayout.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            activity?.let{
                val s = updateOtpValueIndex()
                AppLogger.log("s : $s")
                if (s.isNotEmpty() && s.length == 6){
                    if (!progressDialog.isShowing)
                        progressDialog.show()
                    loginViewModel?.getLoginWithOtp(s)
                }else Toast.makeText(it,"Please enter valid Otp",Toast.LENGTH_SHORT).show()
            }
        }

        binding.p1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.p1.text.toString().isNotEmpty()) {
                    binding.p2.requestFocus()

                }
            }
        })
        binding.p2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.p2.text.toString().isNotEmpty()) {
                    binding.p3.requestFocus()
                } else binding.p1.requestFocus()
            }
        })
        binding.p3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.p3.text.toString().isNotEmpty())
                    binding.p4.requestFocus()
                else binding.p2.requestFocus()
            }
        })
        binding.p4.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.p4.text.toString().isNotEmpty())
                    binding.p5.requestFocus()
                else binding.p3.requestFocus()
            }
        })
        binding.p5.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.p5.text.toString().isNotEmpty())
                    binding.p6.requestFocus()
                else binding.p4.requestFocus()
            }
        })
        binding.p6.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.p6.text.toString().isNotEmpty())
                    Utils.hideKeyboard(requireContext(), binding.p6)
                else binding.p5.requestFocus()
            }
        })
        if (loginViewModel?.loginResponse?.hasActiveObservers() == true){
            loginViewModel?.loginResponse?.removeObservers(viewLifecycleOwner)
        }
        loginViewModel?.loginResponse?.observe(viewLifecycleOwner) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()
            if (it != null && it.data?.access?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS && it.data.access?.isNotEmpty() == true) {
                    AppPreferences.getInstance().saveString("accessToken", it.data.access)
                    AppPreferences.getInstance().saveString("refreshToken", it.data.refresh)
                    Log.d("status","loginResponse accessToken ${it.data.access}")
                    Toast.makeText(requireActivity(),"Otp verification successful", Toast.LENGTH_LONG).show()
                    findNavController().navigate(ForgotPassStep2Directions.actionForgotPassStep2ToForgotPassStep3())
                    return@observe
                }else{
                    Log.d("status","${it.message}")
                    Toast.makeText(requireActivity(),"error:"+it.message, Toast.LENGTH_LONG).show()
                    enableErrorText()
                }
            }else{
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
                enableErrorText()
            }

        }
    }
    private fun updateOtpValueIndex() : String{
        return binding.p1.text.toString() +
                binding.p2.text.toString()+
                binding.p3.text.toString()+
                binding.p4.text.toString()+
                binding.p5.text.toString()+
                binding.p6.text.toString()
    }

}