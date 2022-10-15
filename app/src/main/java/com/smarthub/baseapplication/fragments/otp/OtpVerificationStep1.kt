package com.smarthub.baseapplication.fragments.otp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OtpVerificationStep1FragmentBinding
import com.smarthub.baseapplication.model.otp.SendOtpService
import com.smarthub.baseapplication.otphelper.AppSignatureHelper
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.OtpViewModel

@Suppress("DEPRECATION")
class OtpVerificationStep1 : Fragment() {

    var binding: OtpVerificationStep1FragmentBinding? = null
    lateinit var viewModel: OtpViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.otp_verification_step1_fragment, container, false)
        binding = OtpVerificationStep1FragmentBinding.bind(view)
        viewModel = ViewModelProvider(this).get(OtpViewModel::class.java)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.back).setOnClickListener {
            Utils.hideKeyboard(requireContext(), it)
            activity?.let {
                it.onBackPressed()
            }
        }

        val regFragment2 = OtpVerificationStep3()
        binding!!.nextLayout.setOnClickListener {
            Utils.hideKeyboard(requireContext(), it)
            viewModel.sendOtp(
                SendOtpService(
                    device_id = "",
                    key = "",
                    mobile = binding!!.moNoEdit.text.toString(),
                    signature = AppSignatureHelper(context).appSignatures.get(0),
                    username = ""
                )
            )
        }

        binding?.moNoEdit?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.moNoEdit?.text.toString()
                        .isNotEmpty() && binding?.moNoEdit?.text.toString().length >= 10
                )
                    Utils.hideKeyboard(requireContext(), binding?.moNoEdit!!)
            }
        })

        viewModel.otpResponse.observe(requireActivity(), Observer {
            addFragment(regFragment2)
            /*if (it.success!!) {
                viewModel.showToast(context, "Otp send successfully.")
                activity?.let {
                    addFragment(regFragment2)
                }
            } else {
                viewModel.showToast(context, "Something wend wrong.")

            }*/
        })

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


