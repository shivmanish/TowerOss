package com.smarthub.baseapplication.ui.fragments.register

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
import com.smarthub.baseapplication.databinding.RegistrationFirstStepBinding
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


@Suppress("DEPRECATION")
class RegistrationFirstStep : Fragment() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var registrationFirstStepBinding: RegistrationFirstStepBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        registrationFirstStepBinding =
            RegistrationFirstStepBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        return registrationFirstStepBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationFirstStepBinding.textRegister.setOnClickListener {
            Utils.hideKeyboard(requireContext(), it)
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                it.startActivity(intent)
            }
        }

        val regFragment2 = RegistrationSecondStep()
        registrationFirstStepBinding.next.setOnClickListener {

            if (!Utils.isValid(registrationFirstStepBinding.firstName.text.toString())) {
                Snackbar.make(
                    registrationFirstStepBinding.firstName,
                    "Please Fill FirstName ",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (!Utils.isValid(registrationFirstStepBinding.lastName.text.toString())) {
                Snackbar.make(
                    registrationFirstStepBinding.lastName,
                    "Please Fill LastName ",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (!Utils.isValid(registrationFirstStepBinding.emailId.text.toString())) {
                Snackbar.make(
                    registrationFirstStepBinding.emailId,
                    "Please Fill EmailId ",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            if (!Utils.isValid(registrationFirstStepBinding.moNo.text.toString())) {
                Snackbar.make(
                    registrationFirstStepBinding.moNo,
                    "Please Fill PhoneNumber ",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            loginViewModel.registerData!!.last_name =
                registrationFirstStepBinding.lastName.text.toString()
            loginViewModel.registerData!!.username =
                registrationFirstStepBinding.firstName.text.toString()
            loginViewModel.registerData!!.email =
                registrationFirstStepBinding.emailId.text.toString()
            loginViewModel.registerData!!.phone =
                registrationFirstStepBinding.moNo.text.toString()

            Utils.hideKeyboard(requireContext(), it)
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

}


