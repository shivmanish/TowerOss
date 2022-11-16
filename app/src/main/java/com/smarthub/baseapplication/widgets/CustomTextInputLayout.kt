package com.smarthub.baseapplication.widgets

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout


class CustomTextInputLayout : TextInputLayout {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

//    override fun setErrorTextAppearance(errorTextAppearance: Int) {
//        super.setErrorTextAppearance(errorTextAppearance)
//        val states = arrayOf(intArrayOf())
//        val colors = intArrayOf(
//            Color.WHITE
//        )
//        val myList = ColorStateList(states, colors)
//        hintTextColor = myList
//    }
//
//    override fun setError(errorText: CharSequence?) {
//        super.setError(errorText)
//        val states = arrayOf(intArrayOf())
//        val colors = intArrayOf(
//            Color.WHITE
//        )
//        val myList = ColorStateList(states, colors)
//        hintTextColor = myList
//    }

}