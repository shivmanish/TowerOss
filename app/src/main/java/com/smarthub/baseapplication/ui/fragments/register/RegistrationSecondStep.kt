package com.smarthub.baseapplication.ui.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.LoginActivity
import com.smarthub.baseapplication.databinding.RegistrationFirstStepBinding
import com.smarthub.baseapplication.databinding.RegistrationSecondStepBinding
import com.smarthub.baseapplication.ui.adapter.spinner.CustomRegistrationArrayAdapter
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationSecondStep.newInstance] factory method to
 * create an instance of this fragment.
 */
@Suppress("DEPRECATION")
class RegistrationSecondStep : Fragment() {
    lateinit var registrationSecondStepBinding: RegistrationSecondStepBinding
    lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        registrationSecondStepBinding =
            RegistrationSecondStepBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        return registrationSecondStepBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginButton = view.findViewById<View>(R.id.text_register)
        loginButton.setOnClickListener { view ->
            Utils.hideKeyboard(requireContext(),view)
            activity?.let{
                val intent = Intent (it, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                it.startActivity(intent)
            }
        }
        setupAutoCompleteView()

        val regFragment2 = RegistrationThirdStep()
        view.findViewById<View>(R.id.next).setOnClickListener {
            Utils.hideKeyboard(requireContext(),it)
            addFragment(regFragment2)
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

    private fun setupAutoCompleteView() {
        val datalist=getlList()
        registrationSecondStepBinding.developer.setAdapter(CustomRegistrationArrayAdapter(context,datalist))
//        registrationFirstStepBinding.companyNam/e.setInputType(InputType.TYPE_NULL);
        registrationSecondStepBinding.developer.onItemClickListener =
            AdapterView.OnItemClickListener { parent, arg1, position, id ->
//                registrationFirstStepBinding.companyName.text = datalist.get(position)
            }

        registrationSecondStepBinding.technology.setAdapter(CustomRegistrationArrayAdapter(context,datalist))
//        registrationFirstStepBinding.companyNam/e.setInputType(InputType.TYPE_NULL);
        registrationSecondStepBinding.technology.onItemClickListener =
            AdapterView.OnItemClickListener { parent, arg1, position, id ->
//                registrationFirstStepBinding.companyName.text = datalist.get(position)
            }


    }

    private fun getlList(): ArrayList<String> {
        val dataList=ArrayList<String>()
        dataList.add("Jio Fiber")
        dataList.add("Airtel India")
        dataList.add("VI")
        dataList.add("BSNL")
        return dataList
    }


}


