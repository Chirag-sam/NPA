package com.notadeveloper.app.npa;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by krsnv on 16-Mar-17.
 */

public class RetrofitBuilder {
    public static final String API_BASE_URL = "http://ec2-35-160-179-108.us-west-2.compute.amazonaws.com:8080/hik/Dashboard/";

    public static OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor());

    public static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    );

    public static Retrofit retrofit = builder
            .client(httpClient.build())
            .build();
    RetrofitInterface nurse = retrofit.create(RetrofitInterface.class);

    public static <S> S createService(
            Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
