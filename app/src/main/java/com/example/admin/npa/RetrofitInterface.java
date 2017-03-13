package com.example.admin.npa;

import android.util.Base64;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by krsnv on 13-Mar-17.
 */

public interface RetrofitInterface {
    String API_BASE_URL = "https://192.168.0.1:8080/";


    OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addNetworkInterceptor(new StethoInterceptor());
    @GET("/login.jsp")
    Call<Nurse> Login(
            @Field("uname") String username,
            @Field("password") String password);
    Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(
                            GsonConverterFactory.create()
                    );

    Retrofit retrofit =builder
                    .client(httpClient.build())
                    .build();

    RetrofitInterface nurse =  retrofit.create(RetrofitInterface.class);

}
