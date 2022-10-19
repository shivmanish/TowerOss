package com.smarthub.baseapplication.ui.fragments.forgot_password

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ForgotPassStep4FragmentBinding
import com.smarthub.baseapplication.utils.Utils

class ForgotPassStep4 : Fragment() {

    var binding : ForgotPassStep4FragmentBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       var view = inflater.inflate(R.layout.forgot_pass_step4_fragment, container, false)
        binding = ForgotPassStep4FragmentBinding.bind(view)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val regFragment2 = ForgotPassStep5()
        binding?.nextLayout?.setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            activity?.let{
               addFragment(regFragment2)
            }
        }
        binding?.p1?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.p1?.text.toString().isNotEmpty())
                    binding?.p2?.requestFocus()
            }
        })
        binding?.p2?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (binding?.p2?.text.toString().isNotEmpty())
                    binding?.p3?.requestFocus()
                else binding?.p1?.requestFocus()
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


