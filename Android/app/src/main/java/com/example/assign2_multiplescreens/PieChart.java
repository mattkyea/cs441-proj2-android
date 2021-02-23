package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class PieChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        //graph code

        //NOTE - pie chart doesn't make sense with this data
        //unless we come up with ranges based on data
        //will either write that code or make different graph

        com.github.mikephil.charting.charts.PieChart pieChart = findViewById(R.id.chart);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(18.5f, "Green"));
        entries.add(new PieEntry(26.7f, "Yellow"));
        entries.add(new PieEntry(24.0f, "Red"));
        entries.add(new PieEntry(30.8f, "Blue"));
        PieDataSet set = new PieDataSet(entries, "Election Results");
        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh

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