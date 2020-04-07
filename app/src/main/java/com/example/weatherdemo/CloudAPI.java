package com.example.weatherdemo;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.CacheInterceptor;

public class CloudAPI {

    public static final String url = "http://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=e6831708-02b4-4ef8-98fa-4b4ce53459d9";
    public OkHttpClient mHttpClient;

    public CloudAPI() {
    }
    public static CloudAPI getInstance() {
        return new CloudAPI();
    }

    public void getWeather(MainActivity context){

        mHttpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url(url).build();

        Call call = mHttpClient.newCall(request);
        call.enqueue(context);
    }
}
