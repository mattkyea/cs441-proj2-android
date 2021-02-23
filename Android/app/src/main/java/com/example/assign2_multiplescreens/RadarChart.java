package com.example.assign2_multiplescreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;

import java.util.ArrayList;
import java.util.List;

public class RadarChart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);

        //graph code
        com.github.mikephil.charting.charts.RadarChart radarChart = findViewById(R.id.chart);
        List<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(18.5f, "Green"));
        entries.add(new RadarEntry(26.7f, "Yellow"));
        entries.add(new RadarEntry(24.0f, "Red"));
        entries.add(new RadarEntry(30.8f, "Blue"));
        RadarDataSet set = new RadarDataSet(entries, "Election Results");
        RadarData data = new RadarData(set);
        radarChart.setData(data);
        radarChart.invalidate(); // refresh

        //intent for screen to left - PieChart
        Intent pieChartIntent = new Intent(this, PieChart.class);
        pieChartIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//again, nifty trick to restore old page, not make a new one

        //intent for screen to right, DataEntry (i.e. back to start)
        Intent dataEntryIntent = new Intent(this, DataEntry.class);
        dataEntryIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        findViewById(android.R.id.content).getRootView().setOnTouchListener(new SwipeListener(this) {
            @Override
            public void onSwipeLeft() {
                System.out.println("swiped left");
                startActivity(dataEntryIntent);
            }

            @Override
            public void onSwipeRight() {
                System.out.println("swiped right");
                startActivity(pieChartIntent);
            }
        });

    }
}