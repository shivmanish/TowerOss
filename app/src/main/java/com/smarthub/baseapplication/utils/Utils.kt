package com.smarthub.baseapplication.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smarthub.baseapplication.R


object Utils {

    fun log(message: String) {
        Log.i("TAG", message)
    }

    fun log(tag: String, message: String){
        Log.i(tag, message)
    }

    fun flog(message: String){
        Log.e("asdfasdf", ":-: $message :-:")
    }


    fun hideKeyboard(context: Context,view: View){
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // the function which triggered when the VALIDATE button is clicked
    // which validates the email address entered by the user
    fun emailValidator(emailToText: String) : Boolean{
        if (emailToText.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            return true
        }
        return false
    }

    fun addFragment(fragment: Fragment?, activity : AppCompatActivity,resId:Int) {
        val backStateName: String = activity.supportFragmentManager.javaClass.name
        val manager = activity.supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.add(resId, fragment!!)
            transaction.addToBackStack(backStateName)
            transaction.commit()
        }
    }

    fun replaceFragmentFragment(fragment: Fragment?, activity : AppCompatActivity,resId :Int) {
        val backStateName: String = activity.supportFragmentManager.javaClass.name
        val manager = activity.supportFragmentManager
        val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val transaction = manager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.enter,
                R.anim.exit,
                R.anim.pop_enter,
                R.anim.pop_exit
            )
            transaction.replace(resId, fragment!!)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}