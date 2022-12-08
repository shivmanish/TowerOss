package com.smarthub.baseapplication.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

import com.smarthub.baseapplication.network.pojo.site_info.DropDownItem;
import com.smarthub.baseapplication.ui.adapter.spinner.CustomArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomSpinner extends AppCompatSpinner {
    List<DropDownItem> data = new ArrayList();
    DropDownItem selecteddata = null;

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

    public void init() {
        this.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selecteddata = data.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void setSpinnerData(List<DropDownItem> data) {
        this.data = data;
        setAdapter(new CustomArrayAdapter(getContext(), data));
    }
    public void setSpinnerData(List<DropDownItem> data,String seletedString) {
        this.data = data;
        setAdapter(new CustomArrayAdapter(getContext(), data));
        setSelection(getPositionOfItem(seletedString));
    }

    public DropDownItem getSelectedValue() {
        return selecteddata;
    }

    public int getPositionOfItem(String item){
        return getIndex(item);
    }

    private int getIndex( String myString) {

        int index = 0;

        for (int i = 0; i < getCount(); i++) {
            if (getItemAtPosition(i).equals(myString)) {
                index = i;
            }
        }
        return index;
    }
}
