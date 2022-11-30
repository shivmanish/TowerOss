package com.smarthub.baseapplication.model;

import android.widget.RadioGroup;

import com.smarthub.baseapplication.network.RetrofitObjectInstance;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ErrorUtils {

    public static APIError parseError(Response<?> response) {
        Converter<ResponseBody, APIError> converter =
                RetrofitObjectInstance.getInstance().responseBodyConverter(APIError.class, new Annotation[0]);

        APIError error;

        try {
            if (response!=null&&response.errorBody()!=null){
                error = converter.convert(response.errorBody());
            }else {
                error=null;
            }
        } catch (IOException e) {
            return new APIError();
        }

        return error;
    }
}
