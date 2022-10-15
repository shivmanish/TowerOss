package com.smarthub.baseapplication.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.smarthub.baseapplication.BuildConfig;
import com.smarthub.baseapplication.helpers.AppPreferences;
import com.smarthub.baseapplication.network.pojo.RefreshToken;
import com.smarthub.baseapplication.utils.Utils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public  class APIInterceptor {
    private static final String BASE_URL = "http://49.50.77.81:8181/";
    private static final String TAG = "APIInterceptor";
    private static final Retrofit.Builder builder = createInstance();
    public static Retrofit retrofit = builder.build();
    public static Retrofit retrofitt;
    public static OkHttpClient httpClient=getOkHttpClient();
    public static APIClient apiClient ;


    private static Retrofit.Builder createInstance() {

        // create OkHttpBuilder client
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Utils.INSTANCE.log("createInstance REtrofit");
        httpClient.readTimeout(15, TimeUnit.SECONDS);
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.writeTimeout(30, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            httpClient.addNetworkInterceptor(new StethoInterceptor());
            //httpClient.interceptors().add(new LoggingInterceptor());

        }
        // adding refresh token Interceptors
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public synchronized okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Utils.INSTANCE.log("createInstance REtrofit 50-1");

                Request request = chain.request();
                Request request2 = request.newBuilder()
//                        .addHeader("Authorization", getAccessToken())
                        .build();
                Utils.INSTANCE.log("createInstance REtrofit 51");
                Utils.INSTANCE.log("Request:" + request2);

                okhttp3.Response response = chain.proceed(request2);
                //Utils.INSTANCE.log("Request:" + request2);
                // String bodyString=response.body().string();
                //Utils.INSTANCE.log("response:" +bodyString);

                int rescode = response.code();
                if (rescode == 401) {
                    try {
                        Utils.INSTANCE.log("APIInterceptor 401 try");
                        if (refreshToken()) {
                            Utils.INSTANCE.log("APIInterceptor 401 if");
                            response.close();
                            request = request.newBuilder()
                                    .removeHeader("Authorization")
                                    .addHeader("Authorization", getAccessToken())
                                    .build();
                            response = chain.proceed(request);
                            Utils.INSTANCE.log("APIInterceptor 401 request: "+request+" response: "+response.body());

                            return response;
                        } else {
                            Utils.INSTANCE.log("APIInterceptor 401 else");

//              AppPreferences.getInstance().clearSavedData();
                            return response;
                        }
                    } catch (Exception e) {
                        return response;
                    }
                }else{
                    // Utils.INSTANCE.log("else not 401 for request: "+request.url());
                    //Utils.INSTANCE.log("REsponse: "+ Objects.requireNonNull(response.body()).string());
                    return response;
                }

            }
        });
        if (BuildConfig.DEBUG) {
            // logging the req and response only in dev | debug mode only
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);

        }

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()
                ).client(httpClient.build());
    }

    public static APIClient getInstance(){

        return apiClient=getRetrofitInstance().create(APIClient.class);

    }

    private  static  OkHttpClient getOkHttpClient(){

        OkHttpClient okHttpClient = new OkHttpClient();

        Utils.INSTANCE.log("createInstance REtrofit");
        OkHttpClient.Builder builder=okHttpClient.newBuilder();
        builder.readTimeout(60L, TimeUnit.SECONDS);
        builder.connectTimeout(60L, TimeUnit.SECONDS);
        builder.writeTimeout(60L, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG) {
            builder.addNetworkInterceptor(new StethoInterceptor());
            //httpClient.interceptors().add(new LoggingInterceptor());

        }
        // adding refresh token Interceptors
        builder.addInterceptor(new Interceptor() {
            @Override
            public synchronized okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                Utils.INSTANCE.log("createInstance REtrofit 50-2");

                Request request = chain.request();
                Request request2 = request.newBuilder()
                        .addHeader("Authorization", getAccessToken())
                        .method(request.method(),request.body())
                        .build();
                Utils.INSTANCE.log("createInstance REtrofit 51");
                Utils.INSTANCE.log("Request:" + request2+"body: "+request2.body());
                okhttp3.Response response = chain.proceed(request2);

                Utils.INSTANCE.log("response:" + Objects.requireNonNull(response.body()).string());
                // String bodyString=response.body().string();
                //Utils.INSTANCE.log("response:" +bodyString);

                int rescode = response.code();
                if (rescode == 401) {
                    Utils.INSTANCE.log("401 APIINterceptor if");
                    try {
                        if (refreshToken()) {
                            Utils.INSTANCE.log("401 APIINterceptor if under referesh");

                            request = request.newBuilder()
                                    .removeHeader("Authorization")
                                    .addHeader("Authorization", getAccessToken())
                                    .build();
                            response = chain.proceed(request);
                            Utils.INSTANCE.log("401 APIINterceptor if under referesh request: "+request);

                            return response;
                        } else {
//              AppPreferences.getInstance().clearSavedData();
                            return response;
                        }
                    } catch (Exception e) {
                        return response;
                    }
                }
                return response;
            }
        });
        if (BuildConfig.DEBUG) {
            // logging the req and response only in dev | debug mode only
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);

        }
        return  okHttpClient;

    }

    private static Retrofit getRetrofitInstance() {

        return retrofitt=new Retrofit.Builder().baseUrl(BASE_URL)

                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();



    }

    public static APIClient get() {
        //return getInstance();
        return retrofit.create(APIClient.class);

    }

    private static String getAccessToken() {
        String access_token = AppPreferences.getInstance().getString("accessToken");
        return "Bearer " + access_token;
    }

    private static boolean refreshToken() throws Exception {
        URL refreshUrl = new URL(BASE_URL + EndPoints.ACCESS_TOKEN);
        String refreshToken = AppPreferences.getInstance().getString("refreshToken");
//    JSONObject parent = new JSONObject();
//    parent.put("refreshToken", refreshToken);
        HttpURLConnection urlConnection = (HttpURLConnection) refreshUrl.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Authorization", "Bearer " + refreshToken);

        urlConnection.setDoOutput(true);
        OutputStreamWriter wr = new OutputStreamWriter(urlConnection.getOutputStream());
//    wr.write(parent.toString());
        wr.flush();

        int responseCode = urlConnection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            // this gson part is optional , you can read response directly from Json too
            Gson gson = new Gson();
            RefreshToken refreshTokenResult = gson.fromJson(response.toString(), RefreshToken.class);
//      AppPreferences.getInstance().removeItem("accessToken");
            AppPreferences.getInstance().saveString("accessToken", refreshTokenResult.getData());
            Utils.INSTANCE.log("APIInterceptor 401 inside refresh token:"+refreshTokenResult.getData());

            return true;
        } else if (responseCode == 401) {
            Utils.INSTANCE.log("APIInterceptor 401 inside refresh token else if");

            AppPreferences.getInstance().removeItem("accessToken");
            AppPreferences.getInstance().removeItem("refreshToken");
            AppPreferences.getInstance().removeItem("isUserLogin");

        } else {
            AppPreferences.getInstance().removeItem("accessToken");
            AppPreferences.getInstance().removeItem("refreshToken");
            AppPreferences.getInstance().removeItem("isUserLogin");
        }
        return false;
    }
}
