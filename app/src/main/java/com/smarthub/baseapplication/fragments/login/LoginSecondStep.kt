package com.smarthub.baseapplication.fragments.login

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.JsonObject
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.activities.DashboardActivity
import com.smarthub.baseapplication.databinding.LogingSecondStepBinding
import com.smarthub.baseapplication.fragments.forgot_password.ForgotPassStep1
import com.smarthub.baseapplication.fragments.otp.OtpVerificationStep1
import com.smarthub.baseapplication.fragments.register.RegistrationFirstStep
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.network.FetchingUi
import com.smarthub.baseapplication.network.RetrofitObjectInstance
import com.smarthub.baseapplication.utils.Utility
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit


class LoginSecondStep : Fragment() {

    var retrofit: Retrofit? = null
    private lateinit var progressDialog : ProgressDialog
    var binding : LogingSecondStepBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.loging_second_step, container, false)
        binding = LogingSecondStepBinding.bind(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog = ProgressDialog(requireContext())
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        val login = view.findViewById<View>(R.id.login)
        login.setOnClickListener {
            Utility.hideKeyboard(requireContext(),it)
            loginValidation()
        }

        val textRegister = view.findViewById<View>(R.id.text_register)
        textRegister.setOnClickListener {
            Utility.hideKeyboard(requireContext(),it)
            val regFragment1 = RegistrationFirstStep()
            addFragment(regFragment1)
        }

        val forgoPassword = view.findViewById<View>(R.id.forgot_password)
        forgoPassword.setOnClickListener {
            Utility.hideKeyboard(requireContext(),it)
            val regFragment1 = ForgotPassStep1()
            addFragment(regFragment1)
        }

        val signWithPhone = view.findViewById<View>(R.id.sign_with_phone)
        signWithPhone.setOnClickListener {
            Utility.hideKeyboard(requireContext(),it)
            val regFragment1 = OtpVerificationStep1()
            addFragment(regFragment1)
        }
    }

    fun loginValidation(){

//        if (Utility.emailValidator(binding?.userMail?.text.toString()))
//            if (binding?.emailError?.visibility == View.VISIBLE)
//                binding?.emailError?.visibility = View.INVISIBLE
//            else if (binding?.emailError?.visibility == View.INVISIBLE) {
//                binding?.emailError?.visibility = View.VISIBLE
//                return
//            }

//        if (binding?.password?.text.toString().isNotEmpty() && binding?.password?.text.toString().length == 6)
//            if (binding?.forgotError?.visibility == View.VISIBLE)
//                binding?.forgotError?.visibility = View.INVISIBLE
//            else if (binding?.forgotError?.visibility == View.INVISIBLE) {
//                binding?.forgotError?.visibility = View.VISIBLE
//                return
//            }


        progressDialog.show()
        loadJSONFromAsset(binding?.userMail?.text.toString(),binding?.password?.text.toString())

    }

    private fun loadJSONFromAsset(userName:String,userPass :String) {
        Log.d("status","trying to login with mail :$userName ,pass:$userPass")
        retrofit = RetrofitObjectInstance.getInstance()
        val fetchingUi = retrofit!!.create(FetchingUi::class.java)
        fetchingUi.applyAIEffect(UserLoginPost(userName,userPass)).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<JsonObject?> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.d("status","${e.localizedMessage}")
                    if (progressDialog.isShowing)
                        progressDialog.dismiss()
                    Toast.makeText(requireContext(),"error:"+e.localizedMessage, Toast.LENGTH_LONG).show()

                }

                override fun onSuccess(t: JsonObject) {
                    Log.d("status","$t")
                    if (progressDialog.isShowing)
                        progressDialog.dismiss()
                    Toast.makeText(requireActivity(),"LoginSuccessful", Toast.LENGTH_LONG).show()
                    val intent = Intent (requireActivity(), DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
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


