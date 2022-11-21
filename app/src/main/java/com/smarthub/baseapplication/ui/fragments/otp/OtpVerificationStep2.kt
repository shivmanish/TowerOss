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
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OtpVerificationStep2FragmentBinding
import com.smarthub.baseapplication.model.otp.UserOTPGet
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [OtpVerificationStep2.newInstance] factory method to
 * create an instance of this fragment.
 */
class OtpVerificationStep2 : Fragment() {

    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    var binding : OtpVerificationStep2FragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.otp_verification_step2_fragment, container, false)
        binding = OtpVerificationStep2FragmentBinding.bind(view)
        return view
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
                it?.onBackPressed()
            }
        }

        binding?.signWithPhone?.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            if (!progressDialog.isShowing)
                progressDialog.show()
            loginViewModel?.getPhoneOtp(UserOTPGet(binding?.moNoEdit?.text?.toString()))
        }

        binding?.moNoEdit?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.moNoEdit?.text.toString().isNotEmpty() && binding?.moNoEdit?.text.toString().length>=10) {
                    Utils.hideKeyboard(requireContext(), binding?.moNoEdit!!)
                    binding?.signWithPhone?.visibility = View.VISIBLE
                    binding?.signWithPhoneDisable?.visibility = View.GONE
                }else{
                    binding?.signWithPhoneDisable?.visibility = View.VISIBLE
                    binding?.signWithPhone?.visibility = View.GONE
                }
            }
        })

        loginViewModel?.getOtpResponse?.observe(requireActivity()) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()

            if (it?.data != null && it.data.sucesss == true){
                Log.d("status","getOtpResponse ${it.data}")
                activity?.let{
                    val regFragment = OtpVerificationStep3()
                    addFragment(regFragment)
                }
            }else{
                Log.d("status","${AppConstants.GENERIC_ERROR}")
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()
                enableErrorText()
            }
        }
    }

    private fun enableErrorText(){
        binding?.userMailLayout?.error = "enter valid mo no"
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


