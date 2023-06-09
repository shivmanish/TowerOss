package com.smarthub.baseapplication.ui.fragments.register
import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smarthub.baseapplication.databinding.RegistrationSetPassBinding
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


class RegistrationSetPassword : Fragment() {

    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    lateinit var binding : RegistrationSetPassBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = RegistrationSetPassBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        binding.submitPass.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            if (!progressDialog.isShowing)
                progressDialog.show()
            loginViewModel?.registerData?.password = binding.password.text.toString()
            loginViewModel?.registerUser()
        }

        if (loginViewModel?.getRegister()?.hasActiveObservers() == true)
            loginViewModel?.getRegister()?.removeObservers(viewLifecycleOwner)
        loginViewModel?.getRegister()?.observe(viewLifecycleOwner){
            if (progressDialog.isShowing){
                progressDialog.dismiss()
            }
            if (it!=null && it.status == "success"){
                findNavController().
                navigate(RegistrationSetPasswordDirections.
                actionRegistrationSetPasswordToRegistrationOtpFragment())
            }
            else {
                //Toast.makeText(RegistrationSetPassword,"You are Already Register. Go for Login",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun enableErrorPassText(){
        binding.passwordLayout.error = "Password not in correct/above format "
    }

    private fun enableErrorText(){
        binding.confirmPassLayout.error = "Password mismatched or not in correct format"
    }

}


