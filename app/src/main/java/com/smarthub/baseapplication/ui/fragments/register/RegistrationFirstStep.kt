package com.smarthub.baseapplication.ui.fragments.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.LoginActivity
import com.smarthub.baseapplication.databinding.RegistrationFirstStepBinding
import com.smarthub.baseapplication.ui.adapter.spinner.CustomRegistrationArrayAdapter
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


@Suppress("DEPRECATION")
class RegistrationFirstStep : Fragment() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var registrationFirstStepBinding: RegistrationFirstStepBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        registrationFirstStepBinding =
            RegistrationFirstStepBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        return registrationFirstStepBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationFirstStepBinding.companyName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(registrationFirstStepBinding.companyName.text.toString()!=""){
                    registrationFirstStepBinding.companyNameRoot.setEndIconDrawable(R.drawable.check_textview)
                }
                else
                    registrationFirstStepBinding.companyNameRoot.setEndIconDrawable(R.color.transparent)
            }
            override fun afterTextChanged(s: Editable) {
                if(registrationFirstStepBinding.companyName.text.toString()!=""){
                    registrationFirstStepBinding.companyNameRoot.setEndIconDrawable(R.drawable.check_textview)
                }
            }
        })

        registrationFirstStepBinding.textLogin.setOnClickListener { view ->
            Utils.hideKeyboard(requireContext(), view)
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                it.startActivity(intent)
            }
        }
        setupAutoCompleteView()
        val regFragment2 = RegistrationSecondStep()
        registrationFirstStepBinding.next.setOnClickListener {
            if (!Utils.isValid(registrationFirstStepBinding.companyName.text.toString())) {
                registrationFirstStepBinding.companyNameRoot.isErrorEnabled = true
                registrationFirstStepBinding.companyNameRoot.error = "This field can not be empty!"

                return@setOnClickListener
            }else{
                registrationFirstStepBinding.companyNameRoot.isErrorEnabled = false
                registrationFirstStepBinding.companyNameRoot.error = null

            }

            if (!Utils.isValid(registrationFirstStepBinding.firstName.text.toString())) {
               registrationFirstStepBinding.firstNameRoot.isErrorEnabled = true
                registrationFirstStepBinding.firstNameRoot.error = "This field can not be empty!"
                return@setOnClickListener
            }else{
                registrationFirstStepBinding.firstNameRoot.isErrorEnabled = false
                registrationFirstStepBinding.firstNameRoot.error = null
            }
            if (!Utils.isValid(registrationFirstStepBinding.lastName.text.toString())) {
                registrationFirstStepBinding.lastNameRoot.isErrorEnabled = true
                registrationFirstStepBinding.lastNameRoot.error = "This field can not be empty!"

                return@setOnClickListener
            }else{
                registrationFirstStepBinding.lastNameRoot.isErrorEnabled = false
                registrationFirstStepBinding.lastNameRoot.error = null

            }
            if (!Utils.isValid(registrationFirstStepBinding.moNo.text.toString())) {
                registrationFirstStepBinding.moNoRoot.isErrorEnabled = true
                registrationFirstStepBinding.moNoRoot.error = "This field can not be empty!"

                return@setOnClickListener
            }else{
                registrationFirstStepBinding.moNoRoot.isErrorEnabled = false
                registrationFirstStepBinding.moNoRoot.error = null

            }
            if (!Utils.isValid(registrationFirstStepBinding.emailId.text.toString())) {
                registrationFirstStepBinding.emailIdRoot.isErrorEnabled = true
                registrationFirstStepBinding.emailIdRoot.error = "This field can not be empty!"

                return@setOnClickListener
            }else{
                registrationFirstStepBinding.emailIdRoot.isErrorEnabled = false
                registrationFirstStepBinding.emailIdRoot.error = null

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
//            loginViewModel.registerData!!. =
//                registrationFirstStepBinding.moNo.text.toString()
            Utils.hideKeyboard(requireContext(), it)
            findNavController().navigate(RegistrationFirstStepDirections.actionRegistrationFirstStepToRegistrationSecondStep())
        }


        registrationFirstStepBinding?.moNo?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (registrationFirstStepBinding?.moNo?.text.toString().isNotEmpty() && registrationFirstStepBinding?.moNo?.text.toString().length>=10)
                    Utils.hideKeyboard(requireContext(),registrationFirstStepBinding?.moNo!!)
            }
        })

    }

    private fun setupAutoCompleteView() {
        val datalist=getlList()
        registrationFirstStepBinding.companyName.setAdapter(CustomRegistrationArrayAdapter(context,datalist))
//        registrationFirstStepBinding.companyNam/e.setInputType(InputType.TYPE_NULL);
        registrationFirstStepBinding.companyName.onItemClickListener =
            AdapterView.OnItemClickListener { parent, arg1, position, id ->
//                registrationFirstStepBinding.companyName.text = datalist.get(position)
            }


    }

    private fun getlList(): ArrayList<String> {
        val dataList=ArrayList<String>()
        dataList.add("SmartMile")
        dataList.add("Jio Fiber")
        dataList.add("Airtel India")
        dataList.add("VI")
        dataList.add("")
        return dataList
    }


    fun isFilled(field:TextInputEditText,fieldroot:TextInputLayout):Boolean{
        if (!Utils.isValid(field.text.toString())) {
            fieldroot.isErrorEnabled = true
            fieldroot.error = "This field can not be empty!"
            return false
        }else{
            fieldroot.isErrorEnabled = false
            fieldroot.error = null
        return true
        }
    }

}


