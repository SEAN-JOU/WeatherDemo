package com.example.weatherdemo;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import java.util.ArrayList;

public class MyApplication extends Application {

    public static Context getContext;


    public static MyApplication getInstance(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getContext = this;

        int SDK_INT = android.os.Build.VERSION.SDK_INT;

        if (SDK_INT>8){

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }else {
            StrictMode
                    .setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                            .detectDiskReads()
                            .detectDiskWrites()
                            .detectNetwork()
                            .penaltyLog()
                            .build());
            StrictMode
                    .setVmPolicy(new StrictMode.VmPolicy.Builder()
                            .detectLeakedSqlLiteObjects()
                            .penaltyLog()
                            .penaltyDeath()
                            .build());

        }
    }
}
