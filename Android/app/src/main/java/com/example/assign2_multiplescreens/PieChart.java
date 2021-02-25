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

        float[] xVals = getIntent().getExtras().getFloatArray("x values");
        float[] yVals = getIntent().getExtras().getFloatArray("y values");

        //NOTE - pie chart doesn't make sense with this data
        //so, I'll have a pre existing range: 0-10, 10-50, 50-100, 100-500, >500
        //and find how many of my Y values fit into each (X values are ignored)
        //not practical, but good for this/a demo

        com.github.mikephil.charting.charts.PieChart pieChart = findViewById(R.id.chart);

        int[] numInRanges = new int[5];
        for(float f: yVals){
            if(f>=0 && f<10) numInRanges[0]++;
            else if(f>=10 && f<50) numInRanges[1]++;
            else if(f>=50 && f<100) numInRanges[2]++;
            else if(f>=100 && f<500) numInRanges[3]++;
            else numInRanges[4]++;
        }


        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(numInRanges[0], "0-10"));
        entries.add(new PieEntry(numInRanges[1], "10-50"));
        entries.add(new PieEntry(numInRanges[2], "50-100"));
        entries.add(new PieEntry(numInRanges[3], "100-500"));
        entries.add(new PieEntry(numInRanges[4], ">500"));
        PieDataSet set = new PieDataSet(entries, "Percent of Y-Values in Predefined Ranges");


        PieData data = new PieData(set);
        pieChart.setData(data);
        pieChart.invalidate(); // refresh

        //intent for screen to left - BarGraph
        Intent barGraphIntent = new Intent(this, BarGraph.class);
        barGraphIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);//again, nifty trick to restore old page, not make a new one

        //intent for screen to right, RadarChart
        Intent radarChartIntent = new Intent(this, RadarChart.class);
        radarChartIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

        barGraphIntent.putExtra("x values", xVals);
        barGraphIntent.putExtra("y values", yVals);

        radarChartIntent.putExtra("x values", xVals);
        radarChartIntent.putExtra("y values", yVals);

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