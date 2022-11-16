package com.smarthub.baseapplication.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputLayout;

public class CustomTextInputLayout extends TextInputLayout {
    public CustomTextInputLayout(@NonNull Context context) {
        super(context);
    }

    public CustomTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    //    public CustomTextInputLayout(@NonNull Context context) {
//        super(new ContextThemeWrapper(context,R.style.TextInputLayout));
//    }
//
//    public CustomTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(new ContextThemeWrapper(context,R.style.TextInputLayout));
//    }
//
//    public CustomTextInputLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(new ContextThemeWrapper(context,R.style.TextInputLayout));
//
//    }
}
