package com.smarthub.baseapplication.activities
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarthub.baseapplication.R
import com.smarthub.baseapplication.databinding.ActivityLoginBinding
import com.smarthub.baseapplication.ui.fragments.forgot_password.ForgotPassStep1
import com.smarthub.baseapplication.ui.fragments.login.LoginSecondStep
import com.smarthub.baseapplication.ui.fragments.otp.OtpVerificationStep2
import com.smarthub.baseapplication.ui.fragments.register.RegistrationFirstStep
import com.smarthub.baseapplication.helpers.AppPreferences
import com.smarthub.baseapplication.helpers.Resource
import com.smarthub.baseapplication.model.login.UserLoginPost
import com.smarthub.baseapplication.network.User
import com.smarthub.baseapplication.utils.AppConstants
import com.smarthub.baseapplication.utils.Utils
import com.smarthub.baseapplication.viewmodels.LoginViewModel

class LoginActivity : BaseActivity() {

    var binding : ActivityLoginBinding?=null
    private var loginViewModel : LoginViewModel?=null
    private var user : User?=null

//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount === 0) {
//            super.onBackPressed()
//        } else {
//            supportFragmentManager.popBackStack()
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        setContentView(binding?.root)
        binding?.login?.setOnClickListener {
            Utils.hideKeyboard(this,it)
            loginValidation()
        }
        binding?.textRegister?.setOnClickListener {
            Utils.hideKeyboard(this,it)
            val regFragment1 = RegistrationFirstStep()
            addFragment(regFragment1)
        }

        binding?.forgotPassword?.setOnClickListener {
            Utils.hideKeyboard(this,it)
            val regFragment1 = ForgotPassStep1()
            addFragment(regFragment1)
        }

        binding?.signWithPhone?.setOnClickListener {
            Utils.hideKeyboard(this,it)
            val regFragment1 = OtpVerificationStep2()
            addFragment(regFragment1)
        }

    }

    private fun loginValidation(){
        loginViewModel?.loginResponse?.observe(this) {
            hideLoader()
            if (it != null && it.data?.access?.isNotEmpty() == true) {
                if (it.status == Resource.Status.SUCCESS && it.data!=null) {
                    AppPreferences.getInstance().saveString("accessToken", "${it.data?.access}")
                    AppPreferences.getInstance().saveString("refreshToken", "${it.data?.refresh}")


                    Toast.makeText(this@LoginActivity,"LoginSuccessful",Toast.LENGTH_LONG).show()
                    val intent = Intent (this@LoginActivity, DashboardActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                    return@observe
                }else{

                    Toast.makeText(this@LoginActivity,"error:"+it.message,Toast.LENGTH_LONG).show()
                    loginViewModel?.loginResponse?.removeObservers(this)
                    val regFragment1 = LoginSecondStep()
                    addWithoutStackFragment(regFragment1)
                }
            }
            else{
                Toast.makeText(this@LoginActivity,AppConstants.GENERIC_ERROR,Toast.LENGTH_LONG).show()
                loginViewModel?.loginResponse?.removeObservers(this)
                val regFragment1 = LoginSecondStep()
                addWithoutStackFragment(regFragment1)

            }

        }
        user?.username = binding?.userMail?.text.toString()
        showLoader()
        loginViewModel?.getLoginToken(UserLoginPost(binding?.userMail?.text.toString(),binding?.password?.text.toString()))
    }

    fun addWithoutStackFragment(fragment: Fragment?) {
        val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.add(R.id.fragmentContainerView, fragment!!)
            transaction.commit()
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

}