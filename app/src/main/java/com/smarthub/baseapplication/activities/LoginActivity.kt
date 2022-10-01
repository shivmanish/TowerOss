package com.smarthub.baseapplication.activities

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.gson.JsonObject
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityLoginBinding
import com.smarthub.baseapplication.databinding.LogingSecondStepBinding
import com.smarthub.baseapplication.fragments.forgot_password.ForgotPassStep1
import com.smarthub.baseapplication.fragments.login.LoginSecondStep
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

class LoginActivity : AppCompatActivity() {
    var retrofit: Retrofit? = null
    var binding : LogingSecondStepBinding?=null
    private lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LogingSecondStepBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please Wait...")
        progressDialog.setCanceledOnTouchOutside(true)

        binding?.login?.setOnClickListener {
            Utility.hideKeyboard(this,it)
            loginValidation()
        }

        binding?.textRegister?.setOnClickListener {
            Utility.hideKeyboard(this,it)
            val regFragment1 = RegistrationFirstStep()
            addFragment(regFragment1)
        }

        binding?.forgotPassword?.setOnClickListener {
            Utility.hideKeyboard(this,it)
            val regFragment1 = ForgotPassStep1()
            addFragment(regFragment1)
        }

        binding?.signWithPhone?.setOnClickListener {
            Utility.hideKeyboard(this,it)
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

        if (binding?.password?.text.toString().isNotEmpty() && binding?.password?.text.toString().length == 6)
            if (binding?.forgotError?.visibility == View.VISIBLE)
                binding?.forgotError?.visibility = View.INVISIBLE
            else if (binding?.forgotError?.visibility == View.INVISIBLE) {
                binding?.forgotError?.visibility = View.VISIBLE
                return
            }


        progressDialog.show()
        loadJSONFromAsset(binding?.userMail?.text.toString(),binding?.password?.text.toString())
//        val regFragment1 = LoginSecondStep()
//        addFragment(regFragment1)
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
                    Toast.makeText(this@LoginActivity,"error:"+e.localizedMessage,Toast.LENGTH_LONG).show()
                }

                override fun onSuccess(t: JsonObject) {
                    Log.d("status","$t")
                    if (progressDialog.isShowing)
                        progressDialog.dismiss()
                    Toast.makeText(this@LoginActivity,"LoginSuccessful",Toast.LENGTH_LONG).show()
                    val intent = Intent (this@LoginActivity, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            })
    }

    fun addFragment(fragment: Fragment?) {
        val backStateName: String = supportFragmentManager.javaClass.name
        val manager = supportFragmentManager
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

    override fun onBackPressed() {

        if (supportFragmentManager.backStackEntryCount === 0) {
            super.onBackPressed()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}