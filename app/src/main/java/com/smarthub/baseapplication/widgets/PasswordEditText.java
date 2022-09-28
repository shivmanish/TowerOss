package com.smarthub.baseapplication.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.drawable.DrawableCompat;

import com.smarthub.baseapplication.R;

public class PasswordEditText extends AppCompatEditText {
    private static final int WITHOUT_TINT = -1;
    private static final int DRAWABLE_PADDING_IN_DP = 8;
    private Drawable showPasswordDrawable;
    private Drawable hidePasswordDrawable;
    private boolean passwordIsVisible = false;

    public PasswordEditText(Context context) {
        super(context);
        initializeView(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(attrs);
    }

    private void initializeView(AttributeSet attrs) {
        setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        setTransformationMethod(PasswordTransformationMethod.getInstance());
        if (attrs != null) {
            TypedArray attributes = getContext().getTheme()
                    .obtainStyledAttributes(attrs, R.styleable.PasswordEditText, 0, 0);
            int drawableTint = attributes
                    .getColor(R.styleable.PasswordEditText_drawableTintCompat, WITHOUT_TINT);
            showPasswordDrawable = getDrawable(attributes, R.styleable.PasswordEditText_showDrawable, drawableTint);
            hidePasswordDrawable = getDrawable(attributes, R.styleable.PasswordEditText_hideDrawable, drawableTint);
            if (showPasswordDrawable == null) {
                showPasswordDrawable = getDrawable(getResources().getDrawable(R.drawable.ic_password_visible_24dp), drawableTint);
            }
            if (hidePasswordDrawable == null) {
                hidePasswordDrawable = getDrawable(getResources().getDrawable(R.drawable.ic_password_hidden_24dp), drawableTint);
            }
            if (showPasswordDrawable != null && hidePasswordDrawable != null) {
                setCompoundDrawablesWithIntrinsicBounds(null, null, showPasswordDrawable, null);
                setCompoundDrawablePadding(dp2px(DRAWABLE_PADDING_IN_DP));
            }
            attributes.recycle();
        }
    }

    private Drawable getDrawable(TypedArray attributes, int attribute, int tint) {
        Drawable drawable = attributes.getDrawable(attribute);
        return getDrawable(drawable, tint);
    }

    private Drawable getDrawable(Drawable drawable, int tint) {
        if (drawable != null && tint != WITHOUT_TINT) {
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, tint);
        }
        return drawable;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (showPasswordDrawable != null && hidePasswordDrawable != null) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int drawableWidth = showPasswordDrawable.getIntrinsicWidth();
                int minimumX = (getMeasuredWidth() - drawableWidth);
                if (event.getX() >= minimumX) {
                    changeVisibilityMode();
                }
            }
        }
        return super.onTouchEvent(event);
    }

    private void changeVisibilityMode() {
        requestFocus();
        if (!passwordIsVisible) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, hidePasswordDrawable, null);
            setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            passwordIsVisible = true;
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, showPasswordDrawable, null);
            setTransformationMethod(PasswordTransformationMethod.getInstance());
            passwordIsVisible = false;
        }
    }

    private int dp2px(int value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value,
                getResources().getDisplayMetrics());
    }
}
