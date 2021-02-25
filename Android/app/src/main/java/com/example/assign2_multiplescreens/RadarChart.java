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

        float[] xVals = getIntent().getExtras().getFloatArray("x values");
        float[] yVals = getIntent().getExtras().getFloatArray("y values");


        //same as piechart - radarchart doesn't really make sense for (x,y) values
        //so I'll find and map percentage in predefined ranges
        com.github.mikephil.charting.charts.RadarChart radarChart = findViewById(R.id.chart);

        int[] numInRanges = new int[5];
        for(float f: yVals){
            if(f>=0 && f<10) numInRanges[0]++;
            else if(f>=10 && f<50) numInRanges[1]++;
            else if(f>=50 && f<100) numInRanges[2]++;
            else if(f>=100 && f<500) numInRanges[3]++;
            else numInRanges[4]++;
        }


        List<RadarEntry> entries = new ArrayList<>();
        entries.add(new RadarEntry(numInRanges[0], "0-10"));
        entries.add(new RadarEntry(numInRanges[1], "10-50"));
        entries.add(new RadarEntry(numInRanges[2], "50-100"));
        entries.add(new RadarEntry(numInRanges[3], "100-500"));
        entries.add(new RadarEntry(numInRanges[4], ">500"));
        RadarDataSet set = new RadarDataSet(entries, "Percent of Y-Values in Predefined Ranges");


        RadarData data = new RadarData(set);
        radarChart.setData(data);
        radarChart.invalidate(); // refresh

        //intent for screen to left - PieChart
        Intent pieChartIntent = new Intent(this, PieChart.class);
        pieChartIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//again, nifty trick to restore old page, not make a new one

        //intent for screen to right, DataEntry (i.e. back to start)
        Intent dataEntryIntent = new Intent(this, DataEntry.class);
        dataEntryIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        pieChartIntent.putExtra("x values", xVals);
        pieChartIntent.putExtra("y values", yVals);

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