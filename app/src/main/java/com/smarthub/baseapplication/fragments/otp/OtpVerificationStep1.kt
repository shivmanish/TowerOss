package com.smarthub.baseapplication.fragments.otp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.OtpVerificationStep1FragmentBinding
import com.smarthub.baseapplication.utils.Utils


/**
 * A simple [Fragment] subclass.
 * Use the [OtpVerificationStep1.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class OtpVerificationStep1 : Fragment() {

    var binding : OtpVerificationStep1FragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.otp_verification_step1_fragment, container, false)
        binding = OtpVerificationStep1FragmentBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.back).setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            activity?.let{
                it.onBackPressed()
            }
        }

        val regFragment2 = OtpVerificationStep2()
        view.findViewById<View>(R.id.next_layout).setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            activity?.let{
               addFragment(regFragment2)
            }
        }

        binding?.moNoEdit?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.moNoEdit?.text.toString().isNotEmpty() && binding?.moNoEdit?.text.toString().length>=10)
                    Utils.hideKeyboard(requireContext(),binding?.moNoEdit!!)
            }
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


