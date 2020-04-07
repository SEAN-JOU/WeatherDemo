package com.example.weatherdemo;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.weatherdemo.MainActivity.MAIN_BACK;

/**
 * Created by NB004 on 2018/6/29.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    Activity context;
    ArrayList<WeatherData.WeatherDataResults> results = new ArrayList<>();;

    public WeatherAdapter(ArrayList<WeatherData.WeatherDataResults> results, Activity context) {
        this.results = results;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather,parent,false);
        return  new WeatherAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WeatherData.WeatherDataResults result = results.get(position);

        holder.endTime.setText(result.endTime);
        holder.startTime.setText(result.startTime);
        holder.temperature.setText(result.parameterName3+"°C"+"/"+result.parameterName2+"°C");
        holder.cellLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(context,Main2Activity.class);
                it.putExtra("Data",result);
                context.startActivityForResult(it,MAIN_BACK);
            }
        });
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView endTime,startTime,temperature;
        LinearLayout cellLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            cellLayout = itemView.findViewById(R.id.cell_layout);
            endTime = itemView.findViewById(R.id.end_time);
            startTime = itemView.findViewById(R.id.start_time);
            temperature = itemView.findViewById(R.id.temperature);
        }
    }
}
