package com.smarthub.baseapplication.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smarthub.baseapplication.listeners.DrawableClickListener;

public class AutoResizeTextView extends androidx.appcompat.widget.AppCompatTextView {
    public AutoResizeTextView(@NonNull Context context) {
        super(context);
        init();
        DrawableClickListener l = new DrawableClickListener(DrawableClickListener.DRAWABLE_RIGHT){

            @Override
            public boolean onDrawableClick() {
                return false;
            }
        };
    }

    public AutoResizeTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    void init(){
        setText("My Test");
        setGravity(Gravity.CENTER);
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
