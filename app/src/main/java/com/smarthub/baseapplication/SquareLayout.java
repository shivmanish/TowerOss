package com.smarthub.baseapplication;

import android.content.Context;
import android.util.AttributeSet;

import com.google.android.material.card.MaterialCardView;

public class SquareLayout extends MaterialCardView {
    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if ((mode != 1073741824 || size <= 0) && ((mode2 == 1073741824 && size2 > 0) || size >= size2)) {
            size = size2;
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }
}
