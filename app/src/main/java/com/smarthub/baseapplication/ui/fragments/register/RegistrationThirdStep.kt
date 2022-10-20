package com.smarthub.baseapplication.ui.fragments.register

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.LoginActivity
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

@Suppress("DEPRECATION")
class RegistrationThirdStep : Fragment() {
    lateinit var progressDialog : ProgressDialog
    lateinit var registrationViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.registration_third_step, container, false)
        registrationViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        val loginButton = view.findViewById<View>(R.id.text_register)
        loginButton.setOnClickListener { view ->
            Utils.hideKeyboard(requireContext(), view)
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                it.startActivity(intent)
            }
        }
        view.findViewById<View>(R.id.register).setOnClickListener {
            Utils.hideKeyboard(requireContext(), it)

            Utils.clearBackStack(requireActivity())
            addFragment(regFragment2)

//            if (!progressDialog.isShowing)
//                progressDialog.show()
//            setObserver(view)
//            registrationViewModel.registerUser()
        }
    }

    private val regFragment2 = RegistrationSuccessfull()

    private fun setObserver(view: View) {
        registrationViewModel.regstationResponse!!.observe(viewLifecycleOwner) {
//            if (progressDialog.isShowing)
//                progressDialog.dismiss()
            if (it.status.equals("success")) {

                activity?.let {
                    Utils.clearBackStack(requireActivity())
                    addFragment(regFragment2)
                }
            }
            else {
                if (it.Errors != null) {
                    Snackbar.make(
                        view.findViewById(R.id.register),
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


