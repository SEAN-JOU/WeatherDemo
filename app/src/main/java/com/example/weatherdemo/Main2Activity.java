package com.example.weatherdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    WeatherData.WeatherDataResults weatherDataResults = null;
    TextView endTime,startTime,temperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        endTime = findViewById(R.id.end_time);
        startTime = findViewById(R.id.start_time);
        temperature = findViewById(R.id.temperature);

        if(getIntent().getExtras().getSerializable("Data") != null){
            weatherDataResults = (WeatherData.WeatherDataResults) getIntent().getExtras().getSerializable("Data");
            endTime.setText(weatherDataResults.endTime);
            startTime.setText(weatherDataResults.startTime);
            temperature.setText(weatherDataResults.parameterName3+"°C"+"/"+weatherDataResults.parameterName2+"°C");
        }
    }
}
