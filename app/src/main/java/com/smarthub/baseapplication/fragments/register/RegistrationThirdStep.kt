package com.smarthub.baseapplication.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.LoginActivity
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

@Suppress("DEPRECATION")
class RegistrationThirdStep : Fragment() {
    lateinit var registrationViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.registration_third_step, container, false)
        registrationViewModel =
            ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loginButton = view.findViewById<View>(R.id.text_register)
        loginButton.setOnClickListener {
            Utils.hideKeyboard(requireContext(), it)
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                it.startActivity(intent)
            }
        }
        view.findViewById<View>(R.id.register).setOnClickListener {
            Utils.hideKeyboard(requireContext(), it)
            setObserver(view)
            registrationViewModel.registerUser()

//
        }
    }

    val regFragment2 = RegistrationSuccessfull()

    fun setObserver(view: View) {
        registrationViewModel.regstationResponse!!.observe(requireActivity()) {
            if (it.status.equals("success")) {
                activity?.let {
                    addFragment(regFragment2)
                }
            } else {
                if (it.Errors != null) {
                    Snackbar.make(
                        view.findViewById<View>(R.id.register),
                        it.Errors!!,
                        Snackbar.LENGTH_LONG
                    ).show()
                }else{
                    Snackbar.make(
                        view.findViewById<View>(R.id.register),
                        "Something went wrong!",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
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


