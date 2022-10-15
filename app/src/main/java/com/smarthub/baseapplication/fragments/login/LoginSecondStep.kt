package com.smarthub.baseapplication.fragments.login

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.LogingSecondStepBinding
import com.smarthub.baseapplication.fragments.forgot_password.ForgotPassStep1
import com.smarthub.baseapplication.fragments.otp.OtpVerificationStep2
import com.smarthub.baseapplication.fragments.register.RegistrationFirstStep
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


class LoginSecondStep : Fragment() {

    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    var binding : LogingSecondStepBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.loging_second_step, container, false)
        binding = LogingSecondStepBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        val login = view.findViewById<View>(R.id.login)
        login.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            loginValidation()
        }

        val textRegister = view.findViewById<View>(R.id.text_register)
        textRegister.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            val regFragment1 = RegistrationFirstStep()
            addFragment(regFragment1)
        }

        val forgoPassword = view.findViewById<View>(R.id.forgot_password)
        forgoPassword.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            val regFragment1 = ForgotPassStep1()
            addFragment(regFragment1)
        }

        val signWithPhone = view.findViewById<View>(R.id.sign_with_phone)
        signWithPhone.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            val regFragment1 = OtpVerificationStep2()
            addFragment(regFragment1)
        }

        loginViewModel?.loginResponse?.observe(viewLifecycleOwner) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()
            if (it != null && it.data?.access?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS && it.data!=null) {
                    AppPreferences.getInstance().saveString("accessToken", "${it.data?.access}")
                    AppPreferences.getInstance().saveString("refreshToken", "${it.data?.refresh}")

                    Log.d("status","${it.message}")
                    if (progressDialog.isShowing)
                        progressDialog.dismiss()
                    Toast.makeText(requireActivity(),"LoginSuccessful",Toast.LENGTH_LONG).show()
                    val intent = Intent (requireActivity(), DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    return@observe
                }else{
                    Log.d("status","${it.message}")
                    Toast.makeText(requireActivity(),"error:"+it.message,Toast.LENGTH_LONG).show()
                }
            }
            else{
                Log.d("status","${AppConstants.GENERIC_ERROR}")
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR,Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun loginValidation(){
        if (binding?.userMail?.text.toString().isNotEmpty() && binding?.password?.text.toString().isNotEmpty()) {
            progressDialog.show()
            loginViewModel?.getLoginToken(UserLoginPost(binding?.userMail?.text.toString(),binding?.password?.text.toString()))
        }
    }

    fun addFragment(fragment: Fragment?) {
        val backStateName: String = requireActivity().supportFragmentManager.javaClass.name
        val manager = requireActivity().supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.replace(R.id.fragmentContainerView, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }

}


