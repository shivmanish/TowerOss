package com.smarthub.baseapplication.ui.fragments.otp

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
import com.smarthub.baseapplication.databinding.OtpVerificationStep1FragmentBinding
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

        view.findViewById<View>(R.id.back).setOnClickListener {view->
            Utils.hideKeyboard(requireContext(),view)
            activity?.let{
                findNavController().popBackStack()
            }
        }

        binding.sendOtp.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            if (!progressDialog.isShowing)
                progressDialog.show()
            loginViewModel?.getPhoneOtp(UserOTPGet(binding.moNoEdit.text?.toString()))
        }

        binding.moNoEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding.moNoEdit.text.toString().length>=10) {
                    Utils.hideKeyboard(requireContext(), binding.moNoEdit)
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
        loginViewModel?.getOtpResponse?.observe(requireActivity()) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()

            if (it?.data != null && it.data.sucesss == true){
                Log.d("status","getOtpResponse ${it.data}")
                activity?.let{
                    findNavController().navigate(OtpVerificationStep1Directions.actionOtpVerificationStep1ToOtpVerificationStep2())
                }
            }else{
                Log.d("status", AppConstants.GENERIC_ERROR)
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
                enableErrorText()
            }
        }
    }

    private fun enableErrorText(){
        binding.userMailLayout.error = "enter valid mo no"
    }

}


