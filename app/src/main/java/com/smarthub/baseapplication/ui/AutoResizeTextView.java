package com.smarthub.baseapplication.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AutoResizeTextView extends androidx.appcompat.widget.AppCompatTextView {

    public AutoResizeTextView(@NonNull Context context) {
        super(context);
        init();
    }

    public AutoResizeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init(){
        setText("My Test");
        setGravity(Gravity.CENTER);
    }
}
