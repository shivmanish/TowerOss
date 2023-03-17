package com.smarthub.baseapplication.widgets;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

public class CustomEditField extends TextInputEditText {

    public CustomEditField(@NonNull Context context) {
        super(context);
    }

    public CustomEditField(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
