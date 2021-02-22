package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class PieChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        //graph code


        //intent for screen to left - BarGraph
        Intent barGraphIntent = new Intent(this, BarGraph.class);
        barGraphIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//again, nifty trick to restore old page, not make a new one

        //intent for screen to right, RadarChart
        Intent radarChartIntent = new Intent(this, RadarChart.class);
        radarChartIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        findViewById(android.R.id.content).getRootView().setOnTouchListener(new SwipeListener(this) {
            @Override
            public void onSwipeLeft() {
                System.out.println("swiped left");
                startActivity(radarChartIntent);
            }

            @Override
            public void onSwipeRight() {
                System.out.println("swiped right");
                startActivity(barGraphIntent);
            }
        });

    }
}