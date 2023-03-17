package com.smarthub.baseapplication.widgets

import android.content.Context
import android.text.Editable
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

class CustomEditField : TextInputEditText {
    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    override fun getText(): Editable? {
        var text= super.getText()
        if (text==null || text.toString().isEmpty())
            text="0".toEditable()
        return text
    }

    override fun setText(t: CharSequence?, type: BufferType?) {
        var setTextData=t
        if (setTextData==null || setTextData.isEmpty())
            setTextData="0"
        super.setText(setTextData, type)
    }

}