package com.smarthub.baseapplication.ui.fragments.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.LoginActivity
import com.smarthub.baseapplication.databinding.DropDownListViewBinding
import com.smarthub.baseapplication.databinding.RegistrationFirstStepBinding
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.dropdown.DropDownItem
import com.smarthub.baseapplication.ui.adapter.spinner.CustomRegistrationArrayAdapter
import com.smarthub.baseapplication.utils.AppLogger
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel


@Suppress("DEPRECATION")
class RegistrationFirstStep : Fragment() {

    var list : List<DropDownItem> = ArrayList()
    lateinit var loginViewModel: LoginViewModel
    lateinit var registrationFirstStepBinding: RegistrationFirstStepBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        registrationFirstStepBinding = RegistrationFirstStepBinding.inflate(inflater, container, false)
        loginViewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        return registrationFirstStepBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationFirstStepBinding.emailIdRoot.tag = false
        registrationFirstStepBinding.companyCodeRoot.tag = false
        registrationFirstStepBinding.firstNameRoot.tag = false
        registrationFirstStepBinding.lastNameRoot.tag = false
        registrationFirstStepBinding.moNoRoot.tag = false

        registrationFirstStepBinding.companyCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if(registrationFirstStepBinding.companyCode.text.toString().isNotEmpty()){
                    val enteredCode = registrationFirstStepBinding.companyCode.text.toString()
                    for (i in list){
                        if (i.id == enteredCode){
                            item = i
                            registrationFirstStepBinding.companyCodeRoot.tag=true
                            registrationFirstStepBinding.companyCodeRoot.isErrorEnabled = false
                            return
                        }
                        else{
                            registrationFirstStepBinding.companyCodeRoot.tag=false
                        }
                    }
                }
                else {
                    registrationFirstStepBinding.companyCodeRoot.setEndIconDrawable(0)
                    registrationFirstStepBinding.companyCodeRoot.tag=false
                }
                if (registrationFirstStepBinding.companyCodeRoot.tag==false){
                    registrationFirstStepBinding.companyCodeRoot.setEndIconDrawable(0)
                    registrationFirstStepBinding.companyCodeRoot.isErrorEnabled = true
                    registrationFirstStepBinding.companyCodeRoot.error = "Enter valid company code"
                }
            }
        })
        registrationFirstStepBinding.firstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if(registrationFirstStepBinding.firstName.text.toString().isNotEmpty()){
                    registrationFirstStepBinding.firstNameRoot.setEndIconDrawable(R.drawable.check_textview)
                    registrationFirstStepBinding.firstNameRoot.tag=true
                    registrationFirstStepBinding.firstNameRoot.isErrorEnabled = false
                }
                else{
                    registrationFirstStepBinding.firstNameRoot.setEndIconDrawable(R.color.transparent)
                    registrationFirstStepBinding.firstNameRoot.tag=false
                }



            }
        })
        registrationFirstStepBinding.lastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {  }
            override fun afterTextChanged(s: Editable) {
                if(registrationFirstStepBinding.lastName.text.toString().isNotEmpty()){
                    registrationFirstStepBinding.lastNameRoot.setEndIconDrawable(R.drawable.check_textview)
                    registrationFirstStepBinding.lastNameRoot.tag=true
                    registrationFirstStepBinding.lastNameRoot.isErrorEnabled = false
                }
                else{
                    registrationFirstStepBinding.lastNameRoot.setEndIconDrawable(R.color.transparent)
                    registrationFirstStepBinding.lastNameRoot.tag=false
                }
            }
        })
        registrationFirstStepBinding.moNo.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable) {
                if(registrationFirstStepBinding.moNo.text.toString().length==10){
                    Utils.hideKeyboard(requireContext(), registrationFirstStepBinding.moNo)
                    registrationFirstStepBinding.moNoRoot.setEndIconDrawable(R.drawable.check_textview)
                    registrationFirstStepBinding.moNoRoot.tag=true
                    registrationFirstStepBinding.moNoRoot.isErrorEnabled = false
                }else{
                    registrationFirstStepBinding.moNoRoot.setEndIconDrawable(R.color.transparent)
                    registrationFirstStepBinding.moNoRoot.tag=false
                }


            }
        })
        registrationFirstStepBinding.emailId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if(Utils.isValidEmail(registrationFirstStepBinding.emailId.text.toString())){
                    registrationFirstStepBinding.emailIdRoot.setEndIconDrawable(0)
                    registrationFirstStepBinding.emailIdRoot.isErrorEnabled = false
                    registrationFirstStepBinding.loadingEmailProgress.visibility = View.VISIBLE
                    loginViewModel.emailVerification(registrationFirstStepBinding.emailId.text.toString(), item?.id)
                }
                else {
                    registrationFirstStepBinding.emailIdRoot.setEndIconDrawable(0)
                    registrationFirstStepBinding.emailIdRoot.tag = false
                }

            }
        })


//        registrationFirstStepBinding.companyName.setOnClickListener {
//            setupAutoCompleteView()
//        }
        registrationFirstStepBinding.textLogin.setOnClickListener { view ->
            Utils.hideKeyboard(requireContext(), view)
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                it.startActivity(intent)
            }
        }
        registrationFirstStepBinding.next.setOnClickListener {
            if (registrationFirstStepBinding.companyCodeRoot.tag==false) {
                registrationFirstStepBinding.companyCodeRoot.isErrorEnabled = true
                registrationFirstStepBinding.companyCodeRoot.error = "Enter valid company code"
                return@setOnClickListener
            }

            else if (registrationFirstStepBinding.firstNameRoot.tag==false) {
               registrationFirstStepBinding.firstNameRoot.isErrorEnabled = true
                registrationFirstStepBinding.firstNameRoot.error = "Enter First name"
                return@setOnClickListener
            }
            else if (registrationFirstStepBinding.lastNameRoot.tag==false) {
                registrationFirstStepBinding.lastNameRoot.isErrorEnabled = true
                registrationFirstStepBinding.lastNameRoot.error = "Enter last name "
                return@setOnClickListener
            }
            else if (registrationFirstStepBinding.moNoRoot.tag==false) {
                registrationFirstStepBinding.moNoRoot.isErrorEnabled = true
                registrationFirstStepBinding.moNoRoot.error = "Enter 10 digit mobile number"

                return@setOnClickListener
            }
            else if (registrationFirstStepBinding.emailIdRoot.tag==false) {
                registrationFirstStepBinding.emailIdRoot.isErrorEnabled = true
                registrationFirstStepBinding.emailIdRoot.error = "Please Enter Valid Email ID"
                return@setOnClickListener
            }
            else if (!Utils.isValid(registrationFirstStepBinding.emailId.text.toString())) {
                Snackbar.make(
                    registrationFirstStepBinding.emailId,
                    "Please Fill EmailId ",
                    Snackbar.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            loginViewModel.registerData.last_name =
                registrationFirstStepBinding.lastName.text.toString()
            loginViewModel.registerData.ownername = "${item?.id}"
            loginViewModel.registerData.username =
                registrationFirstStepBinding.firstName.text.toString()
            loginViewModel.registerData.email =
                registrationFirstStepBinding.emailId.text.toString()
            loginViewModel.registerData.phone =
                registrationFirstStepBinding.moNo.text.toString()
            Utils.hideKeyboard(requireContext(), it)
            findNavController().navigate(RegistrationFirstStepDirections.actionRegistrationFirstStepToRegistrationSecondStep())
        }

        if (loginViewModel.dropDownList?.hasActiveObservers() == true)
            loginViewModel.dropDownList?.removeObservers(viewLifecycleOwner)

        loginViewModel.dropDownList?.observe(viewLifecycleOwner){
            if (it?.data != null && it.data.isNotEmpty()){
                this.list = it.data
                if (list.isNotEmpty())
                    item = list[0]
//                registrationFirstStepBinding.companyName.text = it.data[0].name.toEditable()
                Log.d("status"," drop down list size :"+it.data.size)
            }else if(it.Errors!=null){
                Log.d("status","e:"+it.Errors)
            }else{
                Log.d("status","something went wrong")
            }
        }

        if (loginViewModel.emailVerifyOtpResponse?.hasActiveObservers() == true)
            loginViewModel.emailVerifyOtpResponse?.removeObservers(viewLifecycleOwner)

        loginViewModel.emailVerifyOtpResponse?.observe(viewLifecycleOwner) {
            AppLogger.log("Email listner is working")
            registrationFirstStepBinding.loadingEmailProgress.visibility = View.GONE
            if (it.status == Resource.Status.SUCCESS && it.data?.status?.isNotEmpty() == true && it.data.status == "success") {
                Log.d("status","email verified")
//                Toast.makeText(requireActivity(),"email verification successful", Toast.LENGTH_LONG).show()
                registrationFirstStepBinding.emailIdRoot.setEndIconDrawable(R.drawable.check_textview)
                registrationFirstStepBinding.emailIdRoot.tag = true
                registrationFirstStepBinding.emailIdRoot.isErrorEnabled = false
                return@observe
            }else{
                registrationFirstStepBinding.emailIdRoot.setEndIconDrawable(0)
                registrationFirstStepBinding.emailIdRoot.tag = false
                registrationFirstStepBinding.emailIdRoot.isErrorEnabled = true
                registrationFirstStepBinding.emailIdRoot.error = "Please Enter Valid Email ID"
                Log.d("status","${it.message}")
            }

        }
        loginViewModel.fetchCompanyDropDown()
    }

    var item: DropDownItem?=null

    private fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

}


