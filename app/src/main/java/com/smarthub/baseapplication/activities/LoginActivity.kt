package com.smarthub.baseapplication.activities
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.gson.JsonObject
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityLoginBinding
import com.smarthub.baseapplication.fragments.forgot_password.ForgotPassStep1
import com.smarthub.baseapplication.fragments.login.LoginSecondStep
import com.smarthub.baseapplication.fragments.otp.OtpVerificationStep1
import com.smarthub.baseapplication.fragments.register.RegistrationFirstStep
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.network.User
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    var binding : ActivityLoginBinding?=null
    private var loginViewModel : LoginViewModel?=null
    private lateinit var progressDialog : ProgressDialog
    private var user : User?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = findViewById<View>(R.id.login)
        login.setOnClickListener {
            Utils.hideKeyboard(this,it)
            val regFragment1 = LoginSecondStep()
            addFragment(regFragment1)
        }

        val textRegister = findViewById<View>(R.id.text_register)
        textRegister.setOnClickListener {
            Utils.hideKeyboard(this,it)
            val regFragment1 = RegistrationFirstStep()
            addFragment(regFragment1)
        }

        binding?.signWithPhone?.setOnClickListener {
            Utils.hideKeyboard(this,it)
            val regFragment1 = OtpVerificationStep1()
            addFragment(regFragment1)
        }

        loginViewModel?.loginResponse?.observe(this) {
            if (it != null && it.data?.access?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS && it.data!=null) {
                    AppPreferences.getInstance().saveString("accessToken", "${it.data?.access}")
                    AppPreferences.getInstance().saveString("refreshToken", "${it.data?.refresh}")
                    Log.d("status","${it.message}")
                    if (progressDialog.isShowing)
                        progressDialog.dismiss()

                    AppPreferences.getInstance().saveString("userMail",binding?.userMail?.text.toString())
                    AppPreferences.getInstance().saveString("password",binding?.password?.text.toString())

                    Toast.makeText(this@LoginActivity,"LoginSuccessful",Toast.LENGTH_LONG).show()
                    val intent = Intent (this@LoginActivity, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                    return@observe
                }else{
                    Log.d("status","${it.message}")
                    if (progressDialog.isShowing)
                        progressDialog.dismiss()
                    Toast.makeText(this@LoginActivity,"error:"+it.message,Toast.LENGTH_LONG).show()
                    loginViewModel?.loginResponse?.removeObservers(this)
                    val regFragment1 = LoginSecondStep()
                    addFragment(regFragment1)
                }
            }
            else{
                    Log.d("status","${AppConstants.GENERIC_ERROR}")
                    if (progressDialog.isShowing)
                        progressDialog.dismiss()
                    Toast.makeText(this@LoginActivity,AppConstants.GENERIC_ERROR,Toast.LENGTH_LONG).show()
                loginViewModel?.loginResponse?.removeObservers(this)
                    val regFragment1 = LoginSecondStep()
                    addFragment(regFragment1)

            }

        }

    }

    private fun loginValidation(){
        progressDialog.show()
        user?.username = binding?.userMail?.text.toString()
        loginViewModel?.getLoginToken(UserLoginPost(binding?.userMail?.text.toString(),binding?.password?.text.toString()))
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