package com.notadeveloper.app.npa;

import android.support.multidex.MultiDexApplication;

import com.facebook.stetho.Stetho;

/**
 * Created by krsnv on 13-Mar-17.
 */

public class MyApplication extends MultiDexApplication {

    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
