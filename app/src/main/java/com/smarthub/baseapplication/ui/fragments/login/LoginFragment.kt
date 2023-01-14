package com.smarthub.baseapplication.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.BaseActivity
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.LoginFragmentBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.network.User
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


class LoginFragment : Fragment() {

    private var loginViewModel : LoginViewModel?=null
    private var user : User?=null
    var binding : LoginFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.login_fragment, container, false)
        binding = LoginFragmentBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding?.password?.transformationMethod= HideReturnsTransformationMethod.getInstance()
        binding?.login?.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            loginValidation()
        }
        binding?.textRegister?.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegistrationFirstStep())

        }

        binding?.forgotPassword?.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToForgotPassStep1())
        }

        binding?.signWithPhone?.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToOtpVerificationStep1())
        }

        binding?.requestMsg?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (binding?.requestMsg?.text != "Please enter your User Id and Password")
                    binding?.requestMsg?.text = "Please enter your User Id and Password"
            }

        })
    }

    private fun loginValidation(){
        if (loginViewModel?.loginResponse?.hasActiveObservers() == true){
            loginViewModel?.loginResponse?.removeObservers(viewLifecycleOwner)
        }
        loginViewModel?.loginResponse?.observe(viewLifecycleOwner) {
            (requireActivity() as BaseActivity).hideLoader()
            if (it != null && it.data?.access?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS) {
                    AppPreferences.getInstance().saveString("accessToken", "${it.data.access}")
                    AppPreferences.getInstance().saveString("refreshToken", "${it.data.refresh}")


                    Toast.makeText(requireContext(),"LoginSuccessful",Toast.LENGTH_LONG).show()
                    val intent = Intent (requireContext(), DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                    return@observe
                }else{

                    Toast.makeText(requireContext(),"error:"+it.message,Toast.LENGTH_LONG).show()
                    enableErrorMsg()
                }
            }
            else{
                Toast.makeText(requireContext(),AppConstants.GENERIC_ERROR,Toast.LENGTH_LONG).show()
                enableErrorMsg()

            }

        }
        user?.username = binding?.userMail?.text.toString()
        (requireActivity() as BaseActivity).showLoader()
        loginViewModel?.getLoginToken(UserLoginPost(binding?.userMail?.text.toString(),binding?.password?.text.toString()))
    }

    private fun enableErrorMsg(){
        binding?.userMailLayout?.error = "Invalid username"
        binding?.passwordLayout?.error = "Incorrect password"
    }
}


