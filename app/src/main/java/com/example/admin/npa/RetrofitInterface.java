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
import retrofit2.http.Query;

/**
 * Created by krsnv on 13-Mar-17.
 */

public interface RetrofitInterface {
    @GET("login.jsp")
    Call<Nurse> Login(
            @Query("uname") String username,
            @Query("password") String password);


}
