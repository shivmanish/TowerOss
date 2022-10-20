package com.smarthub.baseapplication.ui.fragments.forgot_password

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ForgotPassStep2FragmentBinding
import com.smarthub.baseapplication.databinding.ForgotPasswordFragmentBinding
import com.smarthub.baseapplication.utils.Utils


/**
 * A simple [Fragment] subclass.
 * Use the [ForgotPassStep1.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class ForgotPassStep1 : Fragment() {
    var binding : ForgotPassStep2FragmentBinding?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.forgot_pass_step2_fragment, container, false)
        binding = ForgotPassStep2FragmentBinding.bind(view)
        return view

    }

    fun enableErrorMsg(){
        binding?.errorMessage?.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.back).setOnClickListener { view ->
            Utils.hideKeyboard(requireContext(),view)
            activity?.let{
                it.onBackPressed()
            }
        }

        val regFragment2 = ForgotPassStep5()
        view.findViewById<View>(R.id.next_layout).setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            if(binding?.moNoEdit?.text!=null && !binding?.moNoEdit?.text?.toString().isNullOrEmpty() &&
                binding?.moNoEdit?.text?.toString()?.length == 10) {
                activity?.let {
                    addFragment(regFragment2)
                }
            }else enableErrorMsg()
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


