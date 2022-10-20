package com.smarthub.baseapplication.ui.fragments.forgot_password

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
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.ForgotPassStep5FragmentBinding
import com.smarthub.baseapplication.databinding.OtpVerificationStep3FragmentBinding
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class ForgotPassStep5 : Fragment() {

    var binding : ForgotPassStep5FragmentBinding?=null
    private lateinit var progressDialog : ProgressDialog
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.forgot_pass_step5_fragment, container, false)
        binding = ForgotPassStep5FragmentBinding.bind(view)
        return view

    }

    private fun enableErrorText(){
        binding?.validationError?.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)
        val fragment = ForgotPassStep6()
        view.findViewById<View>(R.id.next_layout).setOnClickListener { view ->
            Utils.hideKeyboard(requireContext(),view)
            activity?.let{
                var s = updateOtpValueIndex()
                AppLogger.log("s : $s")
                if (s.isNotEmpty() && s.length == 6){
//                    if (!progressDialog.isShowing)
//                        progressDialog.show()
                   addFragment(fragment)
                }else{enableErrorText()
                    Toast.makeText(it,"Please enter valid Otp", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding?.p1?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.p1?.text.toString().isNotEmpty()) {
                    binding?.p2?.requestFocus()

                }
            }
        })
        binding?.p2?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.p2?.text.toString().isNotEmpty()) {
                    binding?.p3?.requestFocus()
                } else binding?.p1?.requestFocus()
            }
        })
        binding?.p3?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.p3?.text.toString().isNotEmpty())
                    binding?.p4?.requestFocus()
                else binding?.p2?.requestFocus()
            }
        })
        binding?.p4?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.p4?.text.toString().isNotEmpty())
                    binding?.p5?.requestFocus()
                else binding?.p3?.requestFocus()
            }
        })
        binding?.p5?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.p5?.text.toString().isNotEmpty())
                    binding?.p6?.requestFocus()
                else binding?.p4?.requestFocus()
            }
        })
        binding?.p6?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.p6?.text.toString().isNotEmpty())
                    Utils.hideKeyboard(requireContext(),binding?.p6!!)
                else binding?.p5?.requestFocus()
            }
        })

    }
    private fun updateOtpValueIndex() : String{
        return binding?.p1?.text.toString() +
                binding?.p2?.text.toString()+
                binding?.p3?.text.toString()+
                binding?.p4?.text.toString()+
                binding?.p5?.text.toString()+
                binding?.p6?.text.toString()
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