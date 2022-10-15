package com.smarthub.baseapplication.network;

import android.content.Context;

import com.smarthub.baseapplication.network.repo.LoginRepo;
import com.smarthub.baseapplication.viewmodels.viewmodelfactory.LoginFactory;

public class Injector {

    public static LoginFactory createVideoFactory(Context context) {
        return new LoginFactory(createVideoAnnotationShareRepo(), context);
    }

    private static LoginRepo createVideoAnnotationShareRepo() {
        return LoginRepo.getInstance(APIInterceptor.get());
    }

}
