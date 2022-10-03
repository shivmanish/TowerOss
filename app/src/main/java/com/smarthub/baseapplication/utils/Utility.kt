package com.smarthub.baseapplication.utils

import android.content.Context
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast


object Utility {


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
}