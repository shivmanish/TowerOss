package com.smarthub.baseapplication.fragments.otp

import android.app.ProgressDialog
import android.content.Intent
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
import com.smarthub.baseapplication.databinding.OtpVerificationStep3FragmentBinding
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class OtpVerificationStep3 : Fragment() {
    var binding : OtpVerificationStep3FragmentBinding?=null
    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.otp_verification_step3_fragment, container, false)
        binding = OtpVerificationStep3FragmentBinding.bind(view)
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        val regFragment2 = OtpVerificationStep4()
        view.findViewById<View>(R.id.next_layout).setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            activity?.let{
//               addFragment(regFragment2)

            }
        }

        binding?.p1?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                loginViewModel?.updateOtpValueIndex(binding?.p1?.text.toString(),0)
                if (binding?.p1?.text.toString().isNotEmpty()) {
                    binding?.p2?.requestFocus()

                }
            }
        })
        binding?.p2?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                loginViewModel?.updateOtpValueIndex(binding?.p2?.text.toString(),1)
                if (binding?.p2?.text.toString().isNotEmpty()) {
                    binding?.p3?.requestFocus()
                } else binding?.p1?.requestFocus()
            }
        })
        binding?.p3?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                loginViewModel?.updateOtpValueIndex(binding?.p3?.text.toString(),2)
                if (binding?.p3?.text.toString().isNotEmpty())
                    binding?.p4?.requestFocus()
                else binding?.p2?.requestFocus()
            }
        })
        binding?.p4?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                loginViewModel?.updateOtpValueIndex(binding?.p4?.text.toString(),3)
                if (binding?.p4?.text.toString().isNotEmpty())
                    binding?.p5?.requestFocus()
                else binding?.p3?.requestFocus()
            }
        })
        binding?.p5?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                loginViewModel?.updateOtpValueIndex(binding?.p5?.text.toString(),4)
                if (binding?.p5?.text.toString().isNotEmpty())
                    binding?.p6?.requestFocus()
                else binding?.p4?.requestFocus()
            }
        })
        binding?.p6?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                loginViewModel?.updateOtpValueIndex(binding?.p6?.text.toString(),5)
                if (binding?.p6?.text.toString().isNotEmpty())
                    Utils.hideKeyboard(requireContext(),binding?.p6!!)
                else binding?.p5?.requestFocus()
            }
        })

        loginViewModel?.otp?.observe(requireActivity()) {
            AppLogger.log("it :${it?.size}")
            if (it!=null && it.size == 6){
                var s = ""
                for (i in it){
                    if (i.isNullOrEmpty())
                        return@observe
                    else s += i
                }
                Utils.hideKeyboard(requireContext(),binding?.p6!!)
                loginViewModel?.getLoginWithOtp(s)
            }

        }

        loginViewModel?.loginResponse?.observe(requireActivity()) {
            if (progressDialog.isShowing)
                progressDialog.dismiss()
            if (it != null && it.data?.access?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS && it.data!=null) {
                    AppPreferences.getInstance().saveString("accessToken", "${it.data?.access}")
                    AppPreferences.getInstance().saveString("refreshToken", "${it.data?.refresh}")

                    Log.d("status","${it.message}")
                    if (progressDialog.isShowing)
                        progressDialog.dismiss()

                    addFragment(regFragment2)
                    return@observe
                }else{
                    Log.d("status","${it.message}")
                    Toast.makeText(requireActivity(),"error:"+it.message, Toast.LENGTH_LONG).show()

                }
            }else{
                Log.d("status","${AppConstants.GENERIC_ERROR}")
                Toast.makeText(requireActivity(), AppConstants.GENERIC_ERROR, Toast.LENGTH_LONG).show()

            }

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


