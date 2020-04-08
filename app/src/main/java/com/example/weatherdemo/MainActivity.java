package com.example.weatherdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements Callback {

    RecyclerView recyclerView;
    WeatherAdapter weatherAdapter;
    public ArrayList<WeatherData.WeatherDataResults> weatherDataResults = new ArrayList<>();
    public static final int MAIN_BACK = 1;
    SwipeRefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout =  (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        weatherAdapter = new WeatherAdapter(weatherDataResults,MainActivity.this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(weatherAdapter);

        if(ExampleUtil.isConnected(MainActivity.this)) {
            Toast.makeText(MainActivity.this,"讀取中",Toast.LENGTH_SHORT).show();
            CloudAPI.getInstance().getWeather(MainActivity.this);
        }

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(ExampleUtil.isConnected(MainActivity.this)) {
                            CloudAPI.getInstance().getWeather(MainActivity.this);
                        }
                        refreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == MAIN_BACK) {
            Toast.makeText(MainActivity.this,"歡迎回來",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

        Gson gson = new Gson();
        String string = response.body().string();
        Log.d("aaaaa",string);
        try{
            WeatherData weatherData = gson.fromJson(string, WeatherData.class);
            for (WeatherData.WeatherDataResults result:weatherData.result.results) {
                if(result.locationName.equals("臺北市"))
                    weatherDataResults.add(result);
            }
            weatherAdapter.notifyDataSetChanged();
            Toast.makeText(MainActivity.this,"讀取完成",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
