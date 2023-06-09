package com.smarthub.baseapplication.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.R
import com.example.trackermodule.homepage.BaseActivity
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.LoginFragmentBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.network.User
import com.smarthub.baseapplication.ui.fragments.BaseFragment
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppController
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


class LoginFragment : BaseFragment() {

    private var loginViewModel : LoginViewModel?=null
    private var user : User?=null
    var binding : LoginFragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.login_fragment, container, false)
        binding = LoginFragmentBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding?.password?.transformationMethod= PasswordTransformationMethod.getInstance()
        binding?.login?.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            loginValidation()
        }

//        if (!Utils.isNetworkConnected(requireContext())){
//            binding?.login?.isEnabled = false
//            binding?.login?.backgroundTintList = (ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.grey)))
//        }else{
//            binding?.login?.isEnabled = true
//            binding?.login?.backgroundTintList = (ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.yellow)))
//        }
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

        binding?.passwordHideShow?.setOnClickListener {
            if(binding?.password?.transformationMethod?.equals(PasswordTransformationMethod.getInstance())!!){
                binding?.password?.transformationMethod= HideReturnsTransformationMethod.getInstance()
                binding?.passwordHideShow?.setImageResource(R.drawable.password_eye_open)
            }
            else
            {
                binding?.password?.transformationMethod= PasswordTransformationMethod.getInstance()
                binding?.passwordHideShow?.setImageResource(R.drawable.password_eye_close)
            }
        }
    }

    private fun loginValidation(){
        if (loginViewModel?.loginResponse?.hasActiveObservers() == true){
            loginViewModel?.loginResponse?.removeObservers(viewLifecycleOwner)
        }
        AppPreferences.getInstance().saveString("accessToken", "")
        AppPreferences.getInstance().saveString("refreshToken", "")
        loginViewModel?.loginResponse?.observe(viewLifecycleOwner) {
            (requireActivity() as BaseActivity).hideLoader()
            if (it != null && it.data?.access?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS) {
                    AppPreferences.getInstance().saveString("accessToken", it.data.access)
                    AppPreferences.getInstance().saveString("refreshToken", it.data.refresh)

//                    val loginTime = AppPreferences.getInstance().getLong("loginTime")
//                    val loginTimeDiff = (System.currentTimeMillis() - loginTime)/1000
//                    AppLogger.log("loginTimeDiff:$loginTimeDiff")
                    Toast.makeText(requireContext(),"LoginSuccessful",Toast.LENGTH_LONG).show()
                    AppPreferences.getInstance().saveLong("loginTime",System.currentTimeMillis())

                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToCompanyPickerFragment())
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

//        if (loginViewModel?.profileResponse?.hasActiveObservers() == true){
//            loginViewModel?.profileResponse?.removeObservers(viewLifecycleOwner)
//        }
//        loginViewModel?.profileResponse?.observe(viewLifecycleOwner) {
//            hideLoader()
//            if (it != null && it.status==Resource.Status.SUCCESS) {
//                if (it.data?.isNotEmpty()==true) {
//                    AppController.getInstance().ownerName = it.data[0].ownercode
//                    AppPreferences.getInstance().saveString("company",AppController.getInstance().ownerName)
//                }
//                AppPreferences.getInstance().saveLong("loginTime",System.currentTimeMillis())
//                val intent = Intent (requireContext(), DashboardActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//                requireActivity().finish()
//            }else{
//                Toast.makeText(requireContext(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
//            }
//        }

        user?.username = binding?.userMail?.text.toString()
        if (Utils.isNetworkConnected(requireContext())){
            (requireActivity() as BaseActivity).showLoader()
            AppPreferences.getInstance().saveString("loggedUser",binding?.userMail?.text.toString())
            AppPreferences.getInstance().saveString("loggedPass",binding?.password?.text.toString())
            loginViewModel?.getLoginToken(UserLoginPost(binding?.userMail?.text.toString(),binding?.password?.text.toString()))

        }else{
            val lastUserName = AppPreferences.getInstance().getString("loggedUser")
            val lastUserPass = AppPreferences.getInstance().getString("loggedPass")
            if (lastUserName == binding?.userMail?.text.toString() && lastUserPass == binding?.password?.text.toString()){
                val loginTime = AppPreferences.getInstance().getLong("loginTime")
                val loginTimeDiff = (System.currentTimeMillis() - loginTime)/1000
                AppLogger.log("loginTimeDiff:$loginTimeDiff")
                AppController.getInstance().ownerName = AppPreferences.getInstance().getString("company")
                Toast.makeText(requireContext(),"LoginSuccessful",Toast.LENGTH_LONG).show()
                AppPreferences.getInstance().saveLong("loginTime",System.currentTimeMillis())
                val intent = Intent (requireContext(), DashboardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finish()
            }
            else{
                Toast.makeText(requireContext(),"Enter valid credential", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun enableErrorMsg(){
        binding?.userMailLayout?.error = "Invalid username"
        binding?.passwordLayout?.error = "Incorrect password"
    }
}


