package com.smarthub.baseapplication.ui.fragments.register

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.*
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.databinding.RegistrationOtpConfirmationBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


class RegistrationOtpFragment : Fragment() {
    
    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    lateinit var binding : RegistrationOtpConfirmationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = RegistrationOtpConfirmationBinding.inflate(inflater)
        return binding.root
    }

//    private fun enableErrorText(){
//        binding.userMailLayout.error = "enter valid password"
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        otpCount_timer()
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)
        binding.moNo.text="+91"+loginViewModel?.registerData?.phone.toString()
        loginViewModel?.getRegisterOtp(loginViewModel?.registerData?.phone)
        binding.enableSubmitOtp.setOnClickListener { view ->
            Utils.hideKeyboard(requireContext(),view)
            val s = updateOtpValueIndex()
            AppLogger.log("s : $s")
            if (s.isNotEmpty() && s.length == 6){
                if (!progressDialog.isShowing)
                    progressDialog.show()
            loginViewModel?.registerOTPVerification(s,loginViewModel?.registerData?.phone)
//            activity?.let{
//                val s = updateOtpValueIndex()
//                AppLogger.log("s : $s")
//                if (s.isNotEmpty() && s.length == 6){
//                    if (!progressDialog.isShowing)
//                        progressDialog.show()
//
//                }else Toast.makeText(it,"Please enter valid Otp",Toast.LENGTH_SHORT).show()
            }
        }
        binding.resendOtpText.setOnClickListener {
            loginViewModel?.getRegisterOtp(loginViewModel?.registerData?.phone)
            binding.otpCountDownTimer.visibility=View.VISIBLE
            binding.resendOtpText.visibility=View.GONE
            otpCount_timer()


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
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.p6.text.toString().isNotEmpty()){
                    Utils.hideKeyboard(requireContext(), binding.p6)
                    binding.enableSubmitOtp.visibility =
                        if(updateOtpValueIndex().length==6) View.VISIBLE else View.GONE
                }
                else {
                    binding.disableSubmitOtp.visibility=
                        if(updateOtpValueIndex().length==6) View.GONE else View.VISIBLE
                    binding.p5.requestFocus()
                }
            }
        })


        loginViewModel?.registerVerifyOtpResponse?.observe(viewLifecycleOwner) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()
            if (it.status == Resource.Status.SUCCESS && it.data?.status?.isNotEmpty() == true &&
                it.data.status == "success") {
                Log.d("status","loginResponse accessToken ${it.data.status}")
                Toast.makeText(requireActivity(),"Otp verification successful", Toast.LENGTH_LONG).show()
                findNavController().navigate(RegistrationOtpFragmentDirections.actionRegistrationOtpFragmentToRegistrationSuccessfull())

                return@observe
            }else{
                Log.d("status","${it.message}")
                Toast.makeText(requireActivity(),"error:"+it.message, Toast.LENGTH_LONG).show()

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

    fun otpCount_timer(){
        object : CountDownTimer(40000, 1000) {

            // Callback function, fired on regular interval
            override fun onTick(millisUntilFinished: Long) {
//                val word: Spannable = SpannableString("Resend OTP in <font color=#FFD72B>" + millisUntilFinished / 1000+ "</font> minute?")
//                word.setSpan(ForegroundColorSpan(Color.YELLOW), 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                binding.otpCountDownTimer.setText(Html.fromHtml("<font color=#FFFFFFFF>Resend OTP in </font><font color=#FFD72B>0." + millisUntilFinished / 1000+ "</font> <font color=#FFFFFFFF> minute?</font>"))
            }

            // Callback function, fired
            // when the time is up
            override fun onFinish() {
                binding.otpCountDownTimer.visibility=View.GONE
                binding.resendOtpText.visibility=View.VISIBLE
            }
        }.start()
    }

}


