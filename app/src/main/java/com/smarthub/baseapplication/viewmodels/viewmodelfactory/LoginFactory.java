package com.smarthub.baseapplication.viewmodels.viewmodelfactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.smarthub.baseapplication.network.repo.LoginRepo;
import com.smarthub.baseapplication.viewmodels.LoginViewModel;

public class LoginFactory extends ViewModelProvider.NewInstanceFactory {

    private final Context mContext;
    private final LoginRepo mRepository;

    public LoginFactory(LoginRepo mRepository, Context context) {
        this.mRepository = mRepository;
        mContext=context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new LoginViewModel();
    }
}