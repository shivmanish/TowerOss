package com.smarthub.baseapplication.widgets;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.smarthub.baseapplication.network.pojo.site_info.DropDownItem;
import com.smarthub.baseapplication.ui.adapter.spinner.CustomArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinner extends AppCompatSpinner {
    List<DropDownItem> data = new ArrayList();

    public CustomSpinner(@NonNull Context context) {
        super(context);
    }

    public CustomSpinner(@NonNull Context context, int mode) {
        super(context, mode);
    }

    public CustomSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSpinnerData(List<DropDownItem> data){
        this.data = data;
        setAdapter(new CustomArrayAdapter(getContext(),data));
    }
}
