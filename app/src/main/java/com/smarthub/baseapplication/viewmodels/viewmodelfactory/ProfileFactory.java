package com.smarthub.baseapplication.viewmodels.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.smarthub.baseapplication.network.repo.ProfileRepo;
import com.smarthub.baseapplication.viewmodels.ProfileViewModel;


public class ProfileFactory extends ViewModelProvider.NewInstanceFactory {

    private final Context mContext;
    private final ProfileRepo mRepository;

    public ProfileFactory(ProfileRepo mRepository, Context context) {
        this.mRepository = mRepository;
        mContext=context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ProfileViewModel();
    }
}