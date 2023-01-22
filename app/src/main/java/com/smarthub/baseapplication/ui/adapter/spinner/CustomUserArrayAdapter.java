package com.smarthub.baseapplication.ui.adapter.spinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smarthub.baseapplication.R;
import com.smarthub.baseapplication.ui.alert.model.response.UserDataResponseItem;

import java.util.List;

public class CustomUserArrayAdapter extends ArrayAdapter<UserDataResponseItem> {

    public CustomUserArrayAdapter(Context context, List<UserDataResponseItem> algorithmList) {
        super(context, 0, algorithmList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_view, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.text_view);
        UserDataResponseItem currentItem = getItem(position);
        if (currentItem != null) {
            textViewName.setText(""+currentItem.getFirst_name()+" "+currentItem.getLast_name());
        }
        return convertView;
    }
}
